package com.seeeeeeven7.thExpr.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.seeeeeeven7.thExpr.models.Expr;
import com.seeeeeeven7.thExpr.models.ExprRepository;
import com.seeeeeeven7.thExpr.utility.FileUtility;
import com.seeeeeeven7.thExpr.utility.StringUtility;

@Controller
@RequestMapping(value = "/api/expr")
public class ExprController {
	
	@Autowired
	private ExprRepository exprRepository;
	
	@RequestMapping(value = "", method = RequestMethod.POST)
	@ResponseBody
	public Expr postExpr(@RequestParam("exprFile") MultipartFile exprFile, HttpServletResponse response) throws IOException {
		String md5 = StringUtility.MD5(exprFile.getBytes());
		if (!FileUtility.saveExpr(exprFile)) {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			return null;
		};
		
		List<Expr> exprs = exprRepository.findAllByFileMD5(md5);
		if (exprs.isEmpty()) {
			Expr expr = new Expr();
			expr.setFileMD5(md5);
			exprRepository.save(expr);
			return expr;
		}
		else {
			response.setStatus(HttpServletResponse.SC_CONFLICT);
			return null;
		}
	}
}
