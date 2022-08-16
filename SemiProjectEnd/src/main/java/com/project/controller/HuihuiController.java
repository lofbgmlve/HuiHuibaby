package com.project.controller;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.project.dao.ManagerDao;
import com.project.service.CcDeleteService;
import com.project.service.CcDetailService;
import com.project.service.CcListService;
import com.project.service.CcUpdateFormService;
import com.project.service.CcUpdateService;
import com.project.service.CcWriteFormService;
import com.project.service.CcWriteService;
import com.project.service.CommandProcess;
import com.project.service.CommunityDeleteService;
import com.project.service.CommunityDetailService;
import com.project.service.CommunityListService;
import com.project.service.CommunityWriteFormService;
import com.project.service.CommunityWriteService;
import com.project.service.CsDeleteService;
import com.project.service.CsDetailService;
import com.project.service.CsListService;
import com.project.service.CsUpdateFormService;
import com.project.service.CsUpdateService;
import com.project.service.CsWriteFormService;
import com.project.service.CsWriteService;
import com.project.service.DeleteService;
import com.project.service.FaqDeleteService;
import com.project.service.FaqDetailService;
import com.project.service.FaqListService;
import com.project.service.FaqUpdateFormService;
import com.project.service.FaqUpdateService;
import com.project.service.FaqWriteFormService;
import com.project.service.FaqWriteService;
import com.project.service.JoinFormService;
import com.project.service.JoinResultService;
import com.project.service.LoginFormService;
import com.project.service.LoginService;
import com.project.service.LogoutService;
import com.project.service.MyPageService;
import com.project.service.NoticeDeleteService;
import com.project.service.NoticeDetailService;
import com.project.service.NoticeListService;
import com.project.service.NoticeUpdateService;
import com.project.service.NoticeWriteFormService;
import com.project.service.NoticeWriteProcess;
import com.project.service.OverlapIdCheckService;
import com.project.service.OverlapNicknameCheckService;
import com.project.service.ProductDetailService;
import com.project.service.ProductListService;
import com.project.service.ProductPartListService;
import com.project.service.ProductUpdateFormService;
import com.project.service.ProductUpdateService;
import com.project.service.ProductWriteFormService;
import com.project.service.ProductWriteService;
import com.project.service.ReviewDetailService;
import com.project.service.ReviewListService;
import com.project.service.ReviewUpdateFormService;
import com.project.service.ReviewUpdateService;
import com.project.service.ReviewWriteFormService;
import com.project.service.ReviewWriteService;
import com.project.service.UserUpdateFormService;
import com.project.service.UserUpdateResultService;


// 모델2 방식으로 게시판 구현. 
public class HuihuiController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/*
	 * 뷰 페이지 정보 중에서 앞뒷부분에서 중복되는 데이터 최소화하기 위해서 사용하는 접두어와 접미어를 상수로 설정
	 */
	private final String PREFIX = "/WEB-INF/index.jsp?body=";
	private final String SUFFIX = ".jsp";

	@Override
	public void init() throws ServletException {

		/* 애플리케이션 초기화 파라미터로 읽어온 업로드 파일이 저장될 폴더의 경로 구하고 경로&ㅍㅏ일객체 생성 */
		ServletContext sc = getServletContext();
		String uploadDir = sc.getInitParameter("uploadDir");

		String realPath = sc.getRealPath(uploadDir);
		File parentFile = new File(realPath);

		/*
		 * 파일 객체에 지정한 위치에 디렉토리가 존재하지 않거나 파일 객체가 디렉토리가 아니라면 디렉토리를 생성
		 */

		if (!(parentFile.exists() && parentFile.isDirectory())) {
			parentFile.mkdir();
		}

		sc.setAttribute("uploadDir", uploadDir);
		sc.setAttribute("parentFile", parentFile);
		System.out.println("init : " + parentFile);

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doProcess(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		doProcess(request, response);

	}

	// doget dopost에서 호출하는 메소드 get post방식 요청 모두 처리 메소드
	protected void doProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String requestURI = request.getRequestURI();

		String contextPath = request.getContextPath();
		System.out.println("uri : " + requestURI + "ctxPath : " + contextPath);

		String command = requestURI.substring(contextPath.length());
		System.out.println("command : " + command);

		CommandProcess service = null;
		String viewPage = null;
		
		Boolean isLogin = (Boolean) request.getSession().getAttribute("isLogin");
		
		/*
		 * String mgId = request.getParameter("mg_id"); String mgPw =
		 * request.getParameter("mg_pw"); ManagerDao mdao = new ManagerDao(); int mg =
		 * mdao.checkManager(mgId, mgPw);
		 */

		// 로그인이 안됐으면 로그인 폼으로 리다이렉트한다.
		// 로그인을 실행했을때는 제외 시킨다.
		// 회원가입 실행시에도 제외가 되어야 한다.
		/*
		 * if(Objects.isNull(isLogin) && !command.equals("/login.mvc")) { service = new
		 * LoginFormService(); viewPage = service.requestProcess(request, response); }
		 * else */
		if(command.equals("/productList.mvc")
				||command.equals("/*.mvc")|| command.equals("/index.mvc")) {
				
			 service = new ProductListService();
			 viewPage=service.requestProcess(request, response);
			
		}else if( command.equals("/productDetail.mvc")) {
				
			service= new ProductDetailService();
			viewPage = service.requestProcess(request, response);
			
		}else if(command.equals("/writeForm.mvc")) {
				
			service = new ProductWriteFormService();
			viewPage=service.requestProcess(request, response);
				
		}else if(command.equals("/writeProcess.mvc")) {
				
			service = new ProductWriteService();
			viewPage= service.requestProcess(request, response);
			
		}else if(command.equals("/updateForm.mvc")) {
				
			service = new ProductUpdateFormService();
			viewPage = service.requestProcess(request, response);
			
		}else if(command.equals("/updateProcess.mvc")) {
				
			service = new ProductUpdateService();
			viewPage = service.requestProcess(request, response);
			
		}else if( command.equals("/deleteProcess.mvc")) {
			
			service =new DeleteService();
			viewPage = service.requestProcess(request, response);
			
		} else if(command.equals("/productpart.mvc")) {
			service = new ProductPartListService();
			viewPage = service.requestProcess(request, response);  // part별로 페이지 
			
		} else if (command.equals("/loginForm.mvc")) { // 로그인 메뉴가 클릭된 경우 처리
			service = new LoginFormService();
			viewPage = service.requestProcess(request, response);
			
		} else if (command.equals("/login.mvc")) { // 로그인 폼에서 로그인 버튼이 클릭된 경우 처리

			service = new LoginService();
			viewPage = service.requestProcess(request, response);
			
		} else if (command.equals("/logout.mvc")) { // 로그아웃이 클릭된 경우의 처리
			service = new LogoutService();
			viewPage = service.requestProcess(request, response);			
			
		} else if(command.equals("/myPage.mvc")) { //게시글확인이 클릭된 경우 처리			
			service = new MyPageService();
			viewPage = service.requestProcess(request, response);
		
		// 회원가입 클릭시 폼
		} else if(command.equals("/joinForm.mvc")) {
			
			service = new JoinFormService();
			viewPage = service.requestProcess(request, response);
		
		// 회원가입 - 아이디 중복 체크
		} else if(command.equals("/overlapIdCheck.mvc")) {
			
			service = new OverlapIdCheckService();
			viewPage = service.requestProcess(request, response);
		
		// 회원가입 - 닉네임 중복 체크	
		} else if(command.equals("/overlapNicknameCheck.mvc")) {
			
			service = new OverlapNicknameCheckService();
			viewPage = service.requestProcess(request, response);
		
		// 회원가입 - submit 처리
		} else if(command.equals("/joinResult.mvc")) {
			
			service = new JoinResultService();
			viewPage = service.requestProcess(request, response);
		
		// 정보수정
		}  else if(command.equals("/userUpdateForm.mvc")) {
			
			service = new UserUpdateFormService();
			viewPage = service.requestProcess(request, response);
		
		// 정보수정 - submit
		} else if(command.equals("/userUpdateResult.mvc")) {
			
			service = new UserUpdateResultService();
			viewPage = service.requestProcess(request, response);
		
		// 승환
		} else if(command.equals("/noticeList.mvc")
				|| command.equals("/*.mvc")
				|| command.equals("/index.mvc")) {
			//글 목록보기 요청 
			service = new NoticeListService();
			viewPage = service.requestProcess(request, response);
		} else if(command.equals("/noticeDetail.mvc")) {
			//글 내용보기 요청된경우
			service = new NoticeDetailService();
			viewPage = service.requestProcess(request, response);
		} else if (command.equals("/noticeWriteForm.mvc")) {
			//글 작성 요청
			service = new NoticeWriteFormService();
			viewPage = service.requestProcess(request, response);
		} else if (command.equals("/noticeWriteProcess.mvc")) {
			//글 작성 요청
			service = new NoticeWriteProcess();
			viewPage = service.requestProcess(request, response);
		} else if (command.equals("/noticeUpdateProcess.mvc")) {
			service = new NoticeUpdateService();
			viewPage = service.requestProcess(request, response);
		} else if (command.equals("noticeDeleteService.mvc")) {
			//삭제하기 버튼 클릭
			service = new NoticeDeleteService();
			viewPage = service.requestProcess(request, response);
		
		// 0315 규탁 수정
		} else if(command.equals("/faqList.mvc")) {
			service = new FaqListService();
			viewPage = service.requestProcess(request, response);
			
		} else if(command.contains("/faqDetail.mvc")) {
			System.out.println("!!!");
			service = new FaqDetailService();
			viewPage = service.requestProcess(request, response);
			
		} else if(command.contains("/faqwriteForm.mvc")) {
			service = new FaqWriteFormService();
			viewPage = service.requestProcess(request, response);
			
		} else if(command.contains("/faqwriteProcess.mvc")) {
			
			service = new FaqWriteService();
			viewPage = service.requestProcess(request, response);
			
		} else if(command.contains("/faqupdateForm.mvc")) {
			
			service = new FaqUpdateFormService();
			viewPage = service.requestProcess(request, response);
		
		} else if(command.contains("/faqupdateProcess.mvc")) {
			
			service = new FaqUpdateService();
			viewPage = service.requestProcess(request, response);
		
		} else if(command.contains("/faqdeleteProcess.mvc")) {
			
			service = new FaqDeleteService();
			viewPage = service.requestProcess(request, response);
		
			
		//0315 규탁 수정	
		}	else if(command.equals("/csList.mvc")) {
			
			service = new CsListService();
			viewPage = service.requestProcess(request, response);
			
		}  else if(command.contains("/csDetail.mvc")) {
			
			service = new CsDetailService();
			viewPage = service.requestProcess(request, response);
			
		} else if(command.contains("/cswriteForm.mvc")) {
			
			service = new CsWriteFormService();
			viewPage = service.requestProcess(request, response);
			
		} else if(command.contains("/cswriteProcess.mvc")) {
			
			service = new CsWriteService();
			viewPage = service.requestProcess(request, response);
			
		} else if(command.contains("/csupdateForm.mvc")) {
			
			service = new CsUpdateFormService();
			viewPage = service.requestProcess(request, response);
		
		} else if(command.contains("/csupdateProcess.mvc")) {
			
			service = new CsUpdateService();
			viewPage = service.requestProcess(request, response);
		
		} else if(command.contains("/csdeleteProcess.mvc")) {
			
			service = new CsDeleteService();
			viewPage = service.requestProcess(request, response);
		
			
		// 0315 규탁 수정
		} else if(command.equals("/ccList.mvc")) {
			
			service = new CcListService();
			viewPage = service.requestProcess(request, response);
			
		}  else if(command.contains("/ccDetail.mvc")) {
			
			service = new CcDetailService();
			viewPage = service.requestProcess(request, response);
			
		} else if(command.contains("/ccwriteForm.mvc")) {
			
			service = new CcWriteFormService();
			viewPage = service.requestProcess(request, response);
			
		} else if(command.contains("/ccwriteProcess.mvc")) {
			
			service = new CcWriteService();
			viewPage = service.requestProcess(request, response);
			
		} else if(command.contains("/ccupdateForm.mvc")) {
			
			service = new CcUpdateFormService();
			viewPage = service.requestProcess(request, response);
		
		} else if(command.contains("/ccupdateProcess.mvc")) {
			
			service = new CcUpdateService();
			viewPage = service.requestProcess(request, response);
		
		} else if(command.contains("/ccdeleteProcess.mvc")) {
			
			service = new CcDeleteService();
			viewPage = service.requestProcess(request, response);
		
		// 0315 진성 수정
		} else if(command.equals("/communityList.mvc")) {
	           
	        service = new CommunityListService();
	        viewPage = service.requestProcess(request, response);
	           
	    } else if(command.contains("/communityDetail.mvc")) {
	           
	    	service = new CommunityDetailService();
	        viewPage = service.requestProcess(request, response);
	           
	    } else if(command.contains("/communityWriteForm.mvc")) {
	           
	    	service =  new CommunityWriteFormService();
	        viewPage = service.requestProcess(request, response);
	           
	    } else if(command.contains("/communityWriteProcess.mvc")) {
	           
          service = new CommunityWriteService();
          viewPage = service.requestProcess(request, response);
    
	        } else if(command.contains("/communityDeleteServiec.mvc")) {
	        	
	        	service = new CommunityDeleteService();
	        	viewPage = service.requestProcess(request, response);
	        	
	        } else  if(command.contains("/reviewList.mvc")) {
	           
	           service = new ReviewListService();
	           viewPage = service.requestProcess(request, response);
	           
	        } else if(command.contains("/reviewDetail.mvc")) {
	           
	           service = new ReviewDetailService();
	           viewPage = service.requestProcess(request, response);
	           
	        } else if(command.contains("/reviewWriteForm.mvc")) {
	           
	           service =  new ReviewWriteFormService();
	           viewPage = service.requestProcess(request, response);
	           
	        } else if(command.contains("/reviewWriteProcess.mvc")) {
	           
	           service = new ReviewWriteService();
	           viewPage = service.requestProcess(request, response);
	           
	        } else if(command.contains("/reviewUpdateForm.mvc")) {
	           
	           service = new ReviewUpdateFormService();
	           viewPage = service.requestProcess(request, response);
	        
	        } else if(command.contains("/reviewUpdateProcess.mvc")) {
	           
	           service = new ReviewUpdateService();
	           viewPage = service.requestProcess(request, response);
	        
	        } else if(command.contains("/reviewDeleteServiec.mvc")) {
	        	
	        	service = new CommunityDeleteService();
	        	viewPage = service.requestProcess(request, response);
	        }
			
	
		
		if (viewPage != null) {

			String view = viewPage.split(":")[0];
			System.out.println("view : " + view);

			if (view.equals("r") || view.equals("redirect")) {

				// redirect인 경우 지정한 URL로 Redirect 시킨다.
				response.sendRedirect(viewPage.split(":")[1]);

			} else if (view.equals("f") || view.equals("forward")) {

				/*
				 * 웹 템플릿을 적용하지 않는 Forward인 경우 PREFIX와 SUFFIX를 적용하지 않고 지정한 view로 Forward 한다.
				 **/
				RequestDispatcher rd = request.getRequestDispatcher(viewPage.split(":")[1]);
				rd.forward(request, response);
			
			} else {

				/*   /WEB-INF/index.jsp?body= + product/productList + .jsp
				 * 웹 템플릿을 적용하는 경우 PREFIX와 SUFFIX를 적용해 Forward 한다. PREFIX는 view 정보 중 앞에서 중복되는 부분을
				 * 없애기 위해 사용 SUFFIX는 view 정보 중 뒤에서 중복되는 부분을 없애기 위해 사용
				 **/
				RequestDispatcher rd = request.getRequestDispatcher(PREFIX + view + SUFFIX);
				rd.forward(request, response);
			}
		} // end if(viewPage != null)
		
		
	} // end doProcess();
}
