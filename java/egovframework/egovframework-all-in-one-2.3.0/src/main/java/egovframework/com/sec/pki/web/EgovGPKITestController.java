package egovframework.com.sec.pki.web;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import egovframework.com.sec.pki.service.EgovGPKIService;

/**
 * GPKI(Goverment Public Key Infrastructure) 테스트를 위한 서비스 컨트롤러 클래스
 * @author 공통컴포넌트개발팀 한성곤
 * @since 2009.06.04
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2009.6.4  한성곤          최초 생성
 *
 * </pre>
 */
@Controller
public class EgovGPKITestController {
    
    /** EgovGPKIService */
    @Resource(name = "EgovGPKIService")
    private EgovGPKIService gpkiService;
    
    Logger log = Logger.getLogger(this.getClass());
    
    @RequestMapping("/sec/pki/gpkiTestInit.do")
    public String initRequest(
	    HttpServletRequest request,
	    ModelMap model) throws Exception {
	
	String cmd = "init";
	String target = gpkiService.getServerId();

	model.addAttribute("cmd", cmd);
	model.addAttribute("target", target);
	
	return "sec/pki/EgovGpkiTest";
    }
    
    @RequestMapping("/sec/pki/gpkiTest.do")
    public String handleRequest(
	    @RequestParam("cmd") String cmd,
	    @RequestParam("target") String target,
	    @RequestParam("source") String source,
	    @RequestParam("result") String result,
	    HttpServletRequest request,
	    ModelMap model) throws Exception {
	String cmdInfo = cmd;
	String targetInfo = target;
	
	if (cmdInfo == null || cmdInfo.equals("")) {
	    cmdInfo = "init";
	}
	
	if (targetInfo == null || targetInfo.equals("")) {
	    targetInfo = gpkiService.getServerId();
	}
	
	model.addAttribute("cmd", cmdInfo);
	model.addAttribute("target", targetInfo);
	
	if ("encrypt".equals(cmdInfo)) {
	    byte[] data = gpkiService.encrypt(source.getBytes(), targetInfo);
	    
	    model.addAttribute("source", "");
	    model.addAttribute("result", gpkiService.getBASE64String(data));
	} else if ("decrypt".equals(cmdInfo)) {
	    byte[] data = gpkiService.getDataFromBASE64(result);
	    
	    model.addAttribute("source", new String(gpkiService.decrypt(data)));
	    model.addAttribute("result", "");
	} else if ("sign".equals(cmdInfo)) {
	    byte[] data = gpkiService.sign(source.getBytes());
	    
	    model.addAttribute("source", "");
	    model.addAttribute("result", gpkiService.getBASE64String(data));
	} else if ("verify".equals(cmdInfo)) {
	    byte[] data = gpkiService.getDataFromBASE64(result);
	    
	    model.addAttribute("source", new String(gpkiService.verifySign(data)));
	    model.addAttribute("result", "");
	}
	
	return "sec/pki/EgovGpkiTest";
    }
}
