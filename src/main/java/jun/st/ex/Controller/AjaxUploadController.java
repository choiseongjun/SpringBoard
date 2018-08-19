package jun.st.ex.Controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import javax.annotation.Resource;
import javax.inject.Inject;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;


import jun.st.ex.Service.BoardService;
import jun.st.ex.Util.MediaUtils;
import jun.st.ex.Util.UploadFileUtils;


@Controller
public class AjaxUploadController {
	// 로깅을 위한 변수
		private static final Logger logger = LoggerFactory.getLogger(AjaxUploadController.class);
		
		@Inject
		BoardService boardService;

		// 업로드 디렉토리 servlet-context.xml에 설정되어 있음
		@Resource(name = "uploadPath")
		String uploadPath;

		// 파일첨부 페이지로 이동
		@RequestMapping(value = "upload/uploadAjax", 
				method = RequestMethod.GET)
		public String uploadAjax() {
			return "/upload/uploadAjax";
		}

		// 업로드한 파일은 MultipartFile 변수에 저장됨
		@ResponseBody // json 형식으로 리턴
		@RequestMapping(value = "/upload/uploadAjax", 
		method = RequestMethod.POST, produces = "text/plain;charset=utf-8")
		public ResponseEntity<String> uploadAjax(MultipartFile file) throws Exception {
			// 업로드한 파일 정보와 Http 상태 코드를 함께 리턴
			System.out.println("file");
			return new ResponseEntity<String>(
					UploadFileUtils.uploadFile(uploadPath, file.getOriginalFilename(), file.getBytes()), HttpStatus.OK);
		}

		// 이미지 표시 기능
		@ResponseBody // view가 아닌 data 리턴
		@RequestMapping("/upload/displayFile")
		public ResponseEntity<byte[]> displayFile(String fileName) 
				throws Exception {
			// 서버의 파일을 다운로드하기 위한 스트림
			InputStream in = null; // java.io
			ResponseEntity<byte[]> entity = null;
			try {
				// 확장자 검사
				String formatName = fileName.substring(
						fileName.lastIndexOf(".") + 1);
				MediaType mType = MediaUtils.getMediaType(formatName);
				// 헤더 구성 객체
				HttpHeaders headers = new HttpHeaders();
				// InputStream 생성
				in = new FileInputStream(uploadPath + fileName);
//				if (mType != null) { // 이미지 파일이면
//					headers.setContentType(mType);
//				} else { // 이미지가 아니면
					fileName = fileName.substring(
							fileName.indexOf("_") + 1);
					// 다운로드용 컨텐트 타입
					headers.setContentType(
							MediaType.APPLICATION_OCTET_STREAM);
					// 큰 따옴표 내부에 " \" "
					// 바이트배열을 스트링으로
					// iso-8859-1 서유럽언어
					// new String(fileName.getBytes("utf-8"),"iso-8859-1")
					headers.add("Content-Disposition",
							"attachment; filename=\"" 
									+ new String(
	fileName.getBytes("utf-8"), "iso-8859-1") + "\"");
					// headers.add("Content-Disposition"
					// ,"attachment; filename='"+fileName+"'");
//				}
				// 바이트배열, 헤더
				entity = new ResponseEntity<byte[]>(
						IOUtils.toByteArray(in), headers, HttpStatus.OK);
			} catch (Exception e) {
				e.printStackTrace();
				entity = new ResponseEntity<byte[]>(
						HttpStatus.BAD_REQUEST);
			} finally {
				if (in != null)
					in.close(); // 스트림 닫기
			}
			return entity;
		}
		
		@ResponseBody //뷰가 아닌 데이터를 리턴
		@RequestMapping(value="/upload/deleteFile"
			,method=RequestMethod.POST)
		public ResponseEntity<String> deleteFile(String fileName){
			logger.info("fileName:"+fileName); 
			//확장자 검사
			String formatName=fileName.substring(
					fileName.lastIndexOf(".")+1);
			MediaType mType=MediaUtils.getMediaType(formatName);
			if(mType != null) { //이미지 파일이면 원본이미지 삭제
				String front=fileName.substring(0, 12);
				String end=fileName.substring(14);
//	 		File.separatorChar : 유닉스 / 윈도우즈\	
				new File(uploadPath+(front+end).replace(
						'/',File.separatorChar)).delete();
			}
			//원본 파일 삭제(이미지이면 썸네일 삭제)
			new File(uploadPath+fileName.replace(
					'/',File.separatorChar)).delete();
			//레코드 삭제
			boardService.deleteFile(fileName); 
			
			return new ResponseEntity<String>("deleted"
					,HttpStatus.OK);
		}
	}