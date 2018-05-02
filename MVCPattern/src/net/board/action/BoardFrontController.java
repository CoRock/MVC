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
		
		// �޸��忡 ī���ߴ� ��ü ��θ� �޾ƿ��� �Լ�
		String RequestURI = request.getRequestURI();
		// ������Ʈ �̸��� �޾ƿ��� �Լ�(http://localhost:8181/Model2 ���� �޾ƿ�!)
		String contextPath = request.getContextPath();
		// ��ü���� ���� ���� �ʿ��� �޺κи� ���� ��
		String command = RequestURI.substring(contextPath.length());
		
		ActionForward forward = null;
		// ���� ���ε��� �� �� �ִ� action�̶�� interface�� ����
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
		/* view�� �ѷ��ִ� �κ� */
		
		// � ������� ������ �� �Ǵ�
		if(forward.isRedirect()) {
			response.sendRedirect(forward.getPath());
		} else {
			// �������� ������ ������� �Ѱ��ִ� Ŭ����
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