package org.example.filemanager.web.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.example.filemanager.common.FileManagerException;
import org.example.filemanager.web.model.ManagerState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Using single controller in this webapp, mapped to "/tree"
 */
@Controller
@RequestMapping(method = RequestMethod.GET, value="/tree")
public class TreeController implements HandlerExceptionResolver {
	
	Log log = LogFactory.getLog(TreeController.class);
	
	@Autowired
	private ManagerState managerState;
	
	@RequestMapping("/start")
	public synchronized ModelAndView start(Model model) {
		if (log.isDebugEnabled()) {
			log.debug(managerState);
		}
		ModelAndView res = new ModelAndView("main", "manager", managerState);
		return res;
	}

	/**
	 * Click on expand/collapse image on the left panel
	 * @param id
	 * @return
	 */
	@RequestMapping("/expand/{id}")
	public synchronized ModelAndView expandOrCollapse(@PathVariable int id) {
		
		if (log.isDebugEnabled()) {
			log.debug("Clicked 'tree/expand' with id="+id);
		}
		
		managerState.expandOrCollapse(id);
		
		if (log.isDebugEnabled()) {
			log.debug(managerState);
		}
		ModelAndView res = new ModelAndView("main", "manager", managerState);
		return res; 
	}
	
	/**
	 * Click on folder on the left(tree) panel
	 * @param id
	 * @return
	 */
	@RequestMapping("/open/{id}")
	public synchronized ModelAndView open(@PathVariable int id) {
		
		if (log.isDebugEnabled()) {
			log.debug("Clicked 'tree/open' with id="+id);
		}
		
		managerState.setCurTreeItemIndex(id);
		managerState.setCurFileItemIndex(ManagerState.UNSPECIFIED_INDEX);
		managerState.openCurrentOnFilePanel();
		
		if (log.isDebugEnabled()) {
			log.debug(managerState);
		}
		ModelAndView res = new ModelAndView("main", "manager", managerState);
		
		return res; 
	}
	
	/**
	 * Click on file on the right panel
	 * @param id
	 * @return
	 */
	@RequestMapping("/openFile/{id}")
	public synchronized ModelAndView openFile(@PathVariable int id) {
		
		if (log.isDebugEnabled()) {
			log.debug("Clicked 'tree/openFile' with id="+id);
		}
		
		managerState.setCurFileItemIndex(id);
		
		if (log.isDebugEnabled()) {
			log.debug(managerState);
		}
		ModelAndView res = new ModelAndView("main", "manager", managerState);
		
		return res; 
	}
	
	

	//Setter
	public void setManagerState(ManagerState managerState) {
		this.managerState = managerState;
	}


	/**
	 * Exception handling. When exception occured we adding it to model
	 */
	public ModelAndView resolveException(HttpServletRequest req,
			HttpServletResponse responce, Object handler, Exception ex) {
		ModelAndView res = new ModelAndView("main", "manager", managerState);
		
		if (ex instanceof FileManagerException) {
			res.addObject("errorMessage", ex.getMessage());
		} else {
			res.addObject("errorMessage", "SEVERE ERROR: "+ex.getMessage()+". See logs for details.");
		}
		return res;
	}

}
