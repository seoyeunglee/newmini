package snowProject.service.goods;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.multipart.MultipartFile;

import jakarta.servlet.http.HttpSession;
import snowProject.command.GoodsCommand;
import snowProject.domain.AuthInfoDTO;
import snowProject.domain.FileDTO;
import snowProject.domain.GoodsDTO;
import snowProject.mapper.EmployeeMapper;
import snowProject.mapper.GoodsMapper;

@Service
public class GoodsUpdateService {
	@Autowired
	EmployeeMapper employeeMapper;
	@Autowired
	GoodsMapper goodsMapper;
	
	public void execute(GoodsCommand goodsCommand, HttpSession session, BindingResult result, Model model) {
		GoodsDTO dto = new GoodsDTO();
		dto.setGoodsContents(goodsCommand.getGoodsContents());
		dto.setGoodsName(goodsCommand.getGoodsName());
		dto.setGoodsNum(goodsCommand.getGoodsNum());
		dto.setGoodsPrice(goodsCommand.getGoodsPrice());
		
		AuthInfoDTO auth = (AuthInfoDTO) session.getAttribute("auth");
		String empNum = employeeMapper.getEmpNum(auth.getUserId());
		dto.setUpdateEmpNum(empNum);
		
		URL resource = getClass().getClassLoader().getResource("static/upload");
		String fileDir = resource.getFile();
		System.out.println(fileDir);
		if(goodsCommand.getGoodsMainImage() != null) {
			MultipartFile mf = goodsCommand.getGoodsMainImage();
			String originalFile = mf.getOriginalFilename();
			String extension = originalFile.substring(originalFile.lastIndexOf("."));
			String storeName = UUID.randomUUID().toString().replace("-", "");
			String storeFileName = storeName + extension;
			System.out.println("storeFileName : " + storeFileName);
			File file = new File(fileDir + "/" + storeFileName);
			try {
				mf.transferTo(file);
			} catch (Exception e) {
				e.printStackTrace();
			}
			dto.setGoodsMainImage(originalFile);
			dto.setGoodsMainStoreImage(storeFileName);
		}
		
		String originalTotal = "";
		String storeTotal = "";
		if (!goodsCommand.getGoodsDetailImage()[0].getOriginalFilename().isEmpty()) {
			
			
			for (MultipartFile mf : goodsCommand.getGoodsDetailImage()) {
				
				String originalFile = mf.getOriginalFilename();
				
				String extension = originalFile.substring(originalFile.lastIndexOf("."));
				
				String storeName = UUID.randomUUID().toString().replace("-", "");
				
				String storeFileName = storeName + extension;
				
				File file = new File(fileDir + "/" + storeFileName);
			
				try {
					mf.transferTo(file);
				} catch (Exception e) {
					e.printStackTrace();
				}
				originalTotal += originalFile + "/";
				storeTotal += storeFileName + "/";
			}
		}

	
		List<FileDTO> list = (List<FileDTO>) session.getAttribute("fileList");
		
		GoodsDTO goodsDTO = goodsMapper.selectOne(goodsCommand.getGoodsNum());
		
		if (goodsDTO.getGoodsDetailImage() != null) {
			
			List<String> dbOrg = new ArrayList<>(Arrays.asList(goodsDTO.getGoodsDetailImage().split("[/`]")));
			List<String> dbStore = new ArrayList<>(Arrays.asList(goodsDTO.getGoodsDetailStoreImage().split("[/`]")));
			
			if (list != null) {
				for (FileDTO fdto : list) {
					for (String img : dbOrg) {
						if (fdto.getOrgFile().equals(img)) {
							dbOrg.remove(fdto.getOrgFile());
							dbStore.remove(fdto.getStoreFile());
							break;
						}
					}
				}
			}
			for (String img : dbOrg)
				originalTotal += img + "/";
			for (String img : dbStore)
				storeTotal += img + "/";
		}
		dto.setGoodsDetailStoreImage(storeTotal);
		dto.setGoodsDetailImage(originalTotal);

		int i = goodsMapper.goodsUpdate(dto);
		if (i > 0) {
			if (list != null) {
				for (FileDTO fd : list) {
					File file = new File(fileDir + "/" + fd.getStoreFile());
					if (file.exists())
						file.delete();
				}
			}
		}
	}
}