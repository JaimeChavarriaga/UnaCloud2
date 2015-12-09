package unacloud

import java.util.concurrent.TimeUnit
import java.text.DateFormat
import java.text.SimpleDateFormat

import unacloud.enums.VirtualMachineExecutionStateEnum;

class VirtualMachineExecution {
	
	//-----------------------------------------------------------------
	// Properties
	//-----------------------------------------------------------------
	
	/**
	 * Virtual machine hostname
	 */
    String name
	
	/**
	 * Virtual machine number of processors
	 */
	HardwareProfile hardwareProfile
	
	/**
	 * Net interfaces
	 */
	static hasMany = [interfaces:NetInterface]
	
	/**
	 * Date when the node was started
	 */
	Date startTime
	
	/**
	 * Date when the node was stopped
	 */
	Date stopTime
	
	/**
	 * Actual node state (REQUESTED,COPYING,CONFIGURING,DEPLOYING,DEPLOYED,FAILED,FINISHED)
	 */
	VirtualMachineExecutionStateEnum status
	
	/**
	 * Virtual Machine interface message
	 */
	String message
	
	/**
	 * Physical machine where the node is or was deployed
	 */
	PhysicalMachine executionNode
	
	/**
	 * 
	 * Monitoring system configure in virtual execution
	 */
	MonitorSystem monitorSystem
	
	/**
	 * deployed Image 
	 */
	static belongsTo = [deployImage: DeployedImage]
	
	
	static constraints = {
		executionNode nullable: true
		monitorSystem nullable: true
		stopTime nullable: true 
		startTime nullable:true
	}
	
	//-----------------------------------------------------------------
	// Methods
	//-----------------------------------------------------------------
	
	/**
	 * Calculates and returns the remaining execution time (00h:00m:00s format)
	 * @return formated remaining time
	 */
	def remainingTime(){
		long millisTime=(stopTime.getTime()-System.currentTimeMillis())/1000
		String s = ""+millisTime%60;
        String m = ""+((long)(millisTime/60))%60;
        String h = ""+((long)(millisTime/60/60));
        if(s.length()==1)s="0"+s;
        if(m.length()==1)m="0"+m;
        return h+"h:"+m+"m:"+s+"s"
	}
	
	/**
	 * Calculates and returns the remaining execution time in hours
	 * @return hours of execution remaining
	 */
	int runningTimeInHours(){
		long millisTime=(stopTime.getTime()-startTime.getTime())/1000
		return (millisTime/60/60)+1
	}
	
	
	def saveExecution(){
		for(NetInterface netInterface in interfaces){
			netInterface.save(failOnerror:true)
		}
		this.save(failOnError:true)
	}
	
}