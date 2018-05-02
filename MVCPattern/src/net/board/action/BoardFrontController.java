package net.board.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class BoardFrontController
 */
@WebServlet("/BoardFrontController")
public class BoardFrontController extends HttpServlet {

	private void doProcess(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		
		// 메모장에 카피했던 전체 경로를 받아오는 함수
		String RequestURI = request.getRequestURI();
		// 프로젝트 이름을 받아오는 함수(http://localhost:8181/Model2 까지 받아옴!)
		String contextPath = request.getContextPath();
		// 전체에서 빼면 내가 필요한 뒷부분만 남게 됨
		String command = RequestURI.substring(contextPath.length());
		
		ActionForward forward = null;
		// 동적 바인딩을 할 수 있는 action이라는 interface를 구현
		Action action = null;
		
		if(command.equals("/BoardWrite.bo")) {
			forward = new ActionForward();
			forward.setRedirect(false);
			forward.setPath("./boardview/qna_board_write.jsp");
		}/* else if(command.equals("/BoardReplyAction.bo")) {
			action = new BoardReplyView();
			try {
				forward = action.execute(request, response);
			} catch(Exception e) {
				e.printStackTrace();
			}
		}*/
	
	if(command.equals("/BoardDeleteAction.bo")) {

		action = new BoardListAction();

		try { forward = action.execute(request, response); }
		catch(Exception e) { e.printStackTrace(); }
		
	} else if(command.equals("/BoardList.bo")) {
		System.out.println("test");
		action = new BoardListAction();

		try { forward = action.execute(request, response); }
		catch(Exception e) { e.printStackTrace(); }
		
	} else if(command.equals("/BoardDetailAction.bo")) {

		action = new BoardListAction();

		try { forward = action.execute(request, response); }
		catch(Exception e) { e.printStackTrace(); }
		
	}
	
	/*************************************************************/
		/* view로 뿌려주는 부분 */
		
		// 어떤 방식으로 보내줄 지 판단
		if(forward.isRedirect()) {
			response.sendRedirect(forward.getPath());
		} else {
			// 서블릿에서 포워드 방식으로 넘겨주는 클래스
			RequestDispatcher dispatcher =
					request.getRequestDispatcher(forward.getPath());
			dispatcher.forward(request, response);
		}
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doProcess(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doProcess(request, response);
	}
	
}	// end of servlet