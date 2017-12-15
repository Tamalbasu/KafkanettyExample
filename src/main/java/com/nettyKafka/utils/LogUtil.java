package com.nettyKafka.utils;

import java.util.Date;
import java.util.Map;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.JsonProcessingException;

public class LogUtil {
	
		private Logger logger = LogManager.getLogger(LogUtil.class);
		private String trackingId = "";
		private static final String LOG_MESSAGE_KEY = "api_message";

		private LogUtil() {
			// do nothing
		}
		public LogUtil(Class<?> clazz) {
			
			logger = LogManager.getLogger(clazz);
		}
		/**
		 * @return the trackingId
		 */
		public String getTrackingId() {
			return trackingId;
		}

		/**
		 * @param trackingId
		 *            the trackingId to set
		 */
		public void setTrackingId(String trackingId) {
			this.trackingId = trackingId;
		}


		public void error(String string) {
			
			logger.error(createMessageString(string));

		}
		
		public void warn(String string) {
			logger.warn(createMessageString(string));

		}

		public void info(String string) {

			logger.info(createMessageString(string));
		}

		public void error(Exception e) {
			logger.error(e);

		}

		public void info(Date dtTimeStamp) {
			logger.info(dtTimeStamp);

		}

		public void error(String string, Exception e) {
			logger.error(string, e);

		}

		public void info(Object obj) {
			logger.info(obj);

		}

		public void fatal(String detailedMessage) {
			logger.fatal(detailedMessage);

		}

		public void debug(String string) {
			logger.debug(createMessageString(string));

		}

		public void debug(String string, Exception ex) {
			logger.debug(string, ex);

		}

		public void infoMap(Map<String, String> mapMetrics) {
			
			ObjectMapper objMapper = new ObjectMapper();
			try {
				logger.info(objMapper.writeValueAsString(mapMetrics));
			}
			catch(JsonProcessingException ex) {
				logger.warn("Error logging json message-" + ex.getMessage());
				logger.error(ex);
			}
			
		}
		
		private String createMessageString(String string) {
			return  "{\"" + LOG_MESSAGE_KEY + "\" : \"" + string + "\"}";
			
		}


}
