package in.gf2.seniorplaza.api.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

public class PlazaController {
	@Autowired
	protected ModelMapper mp;
	
	protected final Log logger = LogFactory.getLog(getClass());
}
