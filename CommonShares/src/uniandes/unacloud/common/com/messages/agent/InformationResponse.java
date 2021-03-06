package uniandes.unacloud.common.com.messages.agent;

import uniandes.unacloud.common.com.UnaCloudAbstractResponse;

/**
 * Class used to respond with information from server after one operation
 * @author CesarF
 *
 */
public class InformationResponse extends UnaCloudAbstractResponse{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 923545605946862973L;
	private String message;
	
	public InformationResponse(String message) {
		this.message = message;
	}
	
	public String getMessage() {
		return message;
	}

}
