package org.decoris.process;

import org.adempiere.base.IProcessFactory;
import org.compiere.process.ProcessCall;

/**
 * 
 * @author Tegar N
 *
 */

public class DecorisProcessFactory implements IProcessFactory {

	@Override
	public ProcessCall newProcessInstance(String className) {
		ProcessCall process = null;
		try {
			Class<?> clazz = getClass().getClassLoader().loadClass(className);
			process = (ProcessCall) clazz.newInstance();
		} catch (Exception e) {
		}
		return process;
	}

}
