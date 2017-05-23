package com.gitlily.web.servlet;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.Converter;

import com.gitlily.domain.User;
import com.gitlily.service.UserService;
import com.gitlily.utils.CommonUtils;

public class RegisterServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setCharacterEncoding("UTF-8");
		
		//��ȡ���ݼ�
		Map<String, String[]> parameterMap = request.getParameterMap();
		
		User user = new User();
		
		try {
			ConvertUtils.register(new Converter() {
				@Override
				public Object convert(Class clazz, Object obj) {
					SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
					Date date = null;
					try {
						date = format.parse(obj.toString());
					} catch (ParseException e) {
						e.printStackTrace();
					}
					return date;
				}
			}, Date.class);
			BeanUtils.populate(user, parameterMap);
		} catch (IllegalAccessException | InvocationTargetException e) {
			e.printStackTrace();
		}

//		private String uid;
		user.setUid(CommonUtils.getUUID());
//		private String telephone;
		user.setTelephone(null);
//		private int state; //�ж��û��Ƿ񼤻�
		user.setState(0);
//		private String code; //������
		user.setCode(CommonUtils.getUUID());
		
		//service��
		UserService service = new UserService();
		boolean isRegister = service.regist(user);
		
		//�ж��Ƿ�ɹ�
		if(isRegister) {
			response.sendRedirect(request.getContextPath() + "/registerSuccess.jsp");
		} else {
			response.sendRedirect(request.getContextPath() + "/registerFail.jsp");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
