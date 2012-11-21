package com.linkedin.databus.client.pub;

import com.linkedin.databus.core.data_model.DatabusSubscription;
import com.linkedin.databus2.core.filter.DbusKeyCompositeFilterConfig;

/**
 * TBD : Make EspressoClient get only DatabusEspressoStreamConsumers ?
 * @author pganti
 *
 */

public interface DatabusV3Client extends DatabusClient
{

  /**
   * Subscribes a single consumer to the on-line update stream / bootstrap for the specified subscriptions
   * On a successful call, a registration object is returned 
   *
   * It is possible to specify an id, to be used to identify this registration. If rid is null, 
   * a unique id is generated by the library and returned through the DatabusEspressoRegistration 
   * object ( can be queried through getId() interface )
   * 
   * During a client's lifetime, registrationIds are persistent. i.e., it is possible to stop the 
   * consumer, bring it back and associate it with the same registration @see DatabusEspressoRegistration
   * 
   * @param listener
   * @param rid
   * @param filterConfig
   * @param subs
   * @return
   * @throws DatabusClientException
   */
  public DatabusV3Registration registerDatabusListener(DatabusV3Consumer consumer,
		  											   RegistrationId rid,
		  											   DbusKeyCompositeFilterConfig filterConfig,
		  											   DatabusSubscription ... subs)
         throws DatabusClientException;

  /**
   * Subscribes a group of consumer to the on-line update stream / bootstrap for the specified subscriptions. 
   * The onEvent callbacks will be spread and run in parallel across all consumers
   * 
   * The association between a registration and consumer is 1:N
   * (N > 1) is allowed if and only if all of the consumers have the same subscriptions
   * 
   * @param listeners
   * @param rid
   * @param filterConfig
   * @param subs
   * @return
   * @throws DatabusClientException
   */
  public DatabusV3Registration registerDatabusListener(DatabusV3Consumer[] consumers,
		  									           RegistrationId rid,
		  									           DbusKeyCompositeFilterConfig filterConfig,
		  									           DatabusSubscription ... subs)
         throws DatabusClientException;
  
  /**
   * A registrationId uniquely identifies a DatabusV3Registration object in a given client.
   * 
   * The function below is provided for convenience to retrieve a DatabusEspressoRegistration object 
   * given its id. If there does not exist an object with such an id, it returns null
   * 
   * 
   * @param id
   * @return Return a DatabusRegistration object given its id 
   */
  public DatabusV3Registration getRegistration(RegistrationId rid);

}
