package controllers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.util.UriTemplate;

import entities.Pub;

@Controller
@RequestMapping("/pubs")
public class ApiPubController {
	
	private Map<Integer, Pub> pubs = new HashMap<Integer, Pub>();
	
	{
		pubs.put(1, new Pub(45.748554, "nume", "despre", 21.222149, 1, "strada", true));
		pubs.put(2, new Pub(45.748554, "nume", "despre", 21.222149, 2, "strada", true));
	}
	@RequestMapping(value = "",method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(value = HttpStatus.OK)
	public @ResponseBody List<Pub> getPubs() {
			return new ArrayList<Pub>(pubs.values());
	}
	
	@RequestMapping(value = "/{id}", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(value = HttpStatus.OK)
	public @ResponseBody Pub getPub(@PathVariable("id") int id) {
			return null;
	}
	
	@RequestMapping(value = "/{id}", method=RequestMethod.PUT)
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void updatePub(@PathVariable("id") int id,
			               @RequestBody Pub pub) {
		
	}
	
	@RequestMapping(value = "", method=RequestMethod.POST)
	@ResponseStatus(value = HttpStatus.CREATED)
	public void createPub(@RequestBody Pub pub)
	{
		
	}
	
}
