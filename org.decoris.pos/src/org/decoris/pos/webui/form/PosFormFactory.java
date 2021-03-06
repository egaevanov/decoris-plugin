package org.decoris.pos.webui.form;

import java.util.logging.Level;

import org.adempiere.webui.factory.IFormFactory;
import org.adempiere.webui.panel.ADForm;
import org.adempiere.webui.panel.IFormController;
import org.compiere.util.CLogger;

/**
 * 
 * @author Tegar N
 *
 */
public class PosFormFactory implements IFormFactory {

protected transient CLogger log = CLogger.getCLogger(getClass());

@Override
public ADForm newFormInstance(String formName) {
	 Object form = null;
     if (formName.startsWith("org.decoris.pos.webui.form") || formName.startsWith("org.decoris.pos")) {
           ClassLoader cl = getClass().getClassLoader();
           Class<?> clazz = null; 
	  try 
	  {
		clazz = cl.loadClass(formName);
          }
	 catch (Exception e)
	 {
	    if (log.isLoggable(Level.INFO))
	       log.log(Level.INFO, e.getLocalizedMessage(), e);
            return null;
	 } 
     try
	   {
	    form = clazz.newInstance();
	   }
	  catch (Exception e)
	   {
    	if (log.isLoggable(Level.WARNING))
    	{
	    	log.log(Level.WARNING, e.getLocalizedMessage(), e);
    	}
       }
  
    if (form != null) {
	     if (form instanceof ADForm) {
 			return (ADForm)form;
	     } else if (form instanceof IFormController) {
		IFormController controller = (IFormController) form;
		ADForm adForm = controller.getForm();
		adForm.setICustomForm(controller);
		return adForm;
	     }
         }
     }
     return null;
}

}
