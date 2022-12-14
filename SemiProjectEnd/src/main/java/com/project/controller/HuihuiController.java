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


// ??????2 ???????????? ????????? ??????. 
public class HuihuiController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/*
	 * ??? ????????? ?????? ????????? ?????????????????? ???????????? ????????? ??????????????? ????????? ???????????? ???????????? ???????????? ????????? ??????
	 */
	private final String PREFIX = "/WEB-INF/index.jsp?body=";
	private final String SUFFIX = ".jsp";

	@Override
	public void init() throws ServletException {

		/* ?????????????????? ????????? ??????????????? ????????? ????????? ????????? ????????? ????????? ?????? ????????? ??????&??????????????? ?????? */
		ServletContext sc = getServletContext();
		String uploadDir = sc.getInitParameter("uploadDir");

		String realPath = sc.getRealPath(uploadDir);
		File parentFile = new File(realPath);

		/*
		 * ?????? ????????? ????????? ????????? ??????????????? ???????????? ????????? ?????? ????????? ??????????????? ???????????? ??????????????? ??????
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

	// doget dopost?????? ???????????? ????????? get post?????? ?????? ?????? ?????? ?????????
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

		// ???????????? ???????????? ????????? ????????? ?????????????????????.
		// ???????????? ?????????????????? ?????? ?????????.
		// ???????????? ??????????????? ????????? ????????? ??????.
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
			viewPage = service.requestProcess(request, response);  // part?????? ????????? 
			
		} else if (command.equals("/loginForm.mvc")) { // ????????? ????????? ????????? ?????? ??????
			service = new LoginFormService();
			viewPage = service.requestProcess(request, response);
			
		} else if (command.equals("/login.mvc")) { // ????????? ????????? ????????? ????????? ????????? ?????? ??????

			service = new LoginService();
			viewPage = service.requestProcess(request, response);
			
		} else if (command.equals("/logout.mvc")) { // ??????????????? ????????? ????????? ??????
			service = new LogoutService();
			viewPage = service.requestProcess(request, response);			
			
		} else if(command.equals("/myPage.mvc")) { //?????????????????? ????????? ?????? ??????			
			service = new MyPageService();
			viewPage = service.requestProcess(request, response);
		
		// ???????????? ????????? ???
		} else if(command.equals("/joinForm.mvc")) {
			
			service = new JoinFormService();
			viewPage = service.requestProcess(request, response);
		
		// ???????????? - ????????? ?????? ??????
		} else if(command.equals("/overlapIdCheck.mvc")) {
			
			service = new OverlapIdCheckService();
			viewPage = service.requestProcess(request, response);
		
		// ???????????? - ????????? ?????? ??????	
		} else if(command.equals("/overlapNicknameCheck.mvc")) {
			
			service = new OverlapNicknameCheckService();
			viewPage = service.requestProcess(request, response);
		
		// ???????????? - submit ??????
		} else if(command.equals("/joinResult.mvc")) {
			
			service = new JoinResultService();
			viewPage = service.requestProcess(request, response);
		
		// ????????????
		}  else if(command.equals("/userUpdateForm.mvc")) {
			
			service = new UserUpdateFormService();
			viewPage = service.requestProcess(request, response);
		
		// ???????????? - submit
		} else if(command.equals("/userUpdateResult.mvc")) {
			
			service = new UserUpdateResultService();
			viewPage = service.requestProcess(request, response);
		
		// ??????
		} else if(command.equals("/noticeList.mvc")
				|| command.equals("/*.mvc")
				|| command.equals("/index.mvc")) {
			//??? ???????????? ?????? 
			service = new NoticeListService();
			viewPage = service.requestProcess(request, response);
		} else if(command.equals("/noticeDetail.mvc")) {
			//??? ???????????? ???????????????
			service = new NoticeDetailService();
			viewPage = service.requestProcess(request, response);
		} else if (command.equals("/noticeWriteForm.mvc")) {
			//??? ?????? ??????
			service = new NoticeWriteFormService();
			viewPage = service.requestProcess(request, response);
		} else if (command.equals("/noticeWriteProcess.mvc")) {
			//??? ?????? ??????
			service = new NoticeWriteProcess();
			viewPage = service.requestProcess(request, response);
		} else if (command.equals("/noticeUpdateProcess.mvc")) {
			service = new NoticeUpdateService();
			viewPage = service.requestProcess(request, response);
		} else if (command.equals("noticeDeleteService.mvc")) {
			//???????????? ?????? ??????
			service = new NoticeDeleteService();
			viewPage = service.requestProcess(request, response);
		
		// 0315 ?????? ??????
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
		
			
		//0315 ?????? ??????	
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
		
			
		// 0315 ?????? ??????
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
		
		// 0315 ?????? ??????
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

				// redirect??? ?????? ????????? URL??? Redirect ?????????.
				response.sendRedirect(viewPage.split(":")[1]);

			} else if (view.equals("f") || view.equals("forward")) {

				/*
				 * ??? ???????????? ???????????? ?????? Forward??? ?????? PREFIX??? SUFFIX??? ???????????? ?????? ????????? view??? Forward ??????.
				 **/
				RequestDispatcher rd = request.getRequestDispatcher(viewPage.split(":")[1]);
				rd.forward(request, response);
			
			} else {

				/*   /WEB-INF/index.jsp?body= + product/productList + .jsp
				 * ??? ???????????? ???????????? ?????? PREFIX??? SUFFIX??? ????????? Forward ??????. PREFIX??? view ?????? ??? ????????? ???????????? ?????????
				 * ????????? ?????? ?????? SUFFIX??? view ?????? ??? ????????? ???????????? ????????? ????????? ?????? ??????
				 **/
				RequestDispatcher rd = request.getRequestDispatcher(PREFIX + view + SUFFIX);
				rd.forward(request, response);
			}
		} // end if(viewPage != null)
		
		
	} // end doProcess();
}
