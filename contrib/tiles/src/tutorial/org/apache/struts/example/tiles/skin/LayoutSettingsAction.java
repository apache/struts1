//Source file: H:\\TEMP\\generated\\org\\apache\\struts\\example\\tiles\\skin\\LayoutSettingAction.java

package org.apache.struts.example.tiles.skin;

import org.apache.struts.tiles.actions.TilesAction;
import org.apache.struts.action.ActionForward;
import org.apache.struts.tiles.ComponentContext;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForm;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import javax.servlet.ServletException;

/**
 * Action used to set user skin.
 */
public class LayoutSettingsAction extends TilesAction
{
      /** debug flag */
    public static boolean debug = true;

    /**
     * Process the specified HTTP request, and create the corresponding HTTP
     * response (or forward to another web component that will create it).
     * Return an <code>ActionForward</code> instance describing where and how
     * control should be forwarded, or <code>null</code> if the response has
     * already been completed.
     * This method should be implemented by subclasses.
     *
     * @param context The current Tile context, containing Tile attributes
     * @param mapping The ActionMapping used to select this instance
     * @param actionForm The optional ActionForm bean for this request (if any)
     * @param request The HTTP request we are processing
     * @param response The HTTP response we are creating
     *
     * @exception IOException if an input/output error occurs
     * @exception ServletException if a servlet exception occurs
     */
    public ActionForward perform( ComponentContext context,
                                 ActionMapping mapping,
                                 ActionForm form,
                                 HttpServletRequest request,
                                 HttpServletResponse response)
                          throws IOException, ServletException
   {
    if(debug)
      System.out.println("Enter action LayoutSettingAction");

    LayoutSettingsForm actionForm = (LayoutSettingsForm)form;

        // Load user menu settings and available list of choices
    String  selected = LayoutSwitchAction.getUserSetting( context, request );
    if(selected==null)
      selected = "default";
    System.out.println("user setting retrieved");
    DefinitionCatalog catalog = LayoutSwitchAction.getCatalog( context, request, getServlet().getServletContext() );
    System.out.println("catalog retrieved");

      // Check if form is submitted
      // If true, read, check and store new values submitted by user.
    if( actionForm.isSubmitted() )
      {  // read arrays
      if(debug)
        System.out.println("form submitted");
      selected = catalog.getKey(actionForm.getSelected());
      if(debug)
        System.out.println( "key : " + selected );
      LayoutSwitchAction.setUserSetting(context, request, selected );
      if(debug)
        System.out.println( "settings : " + selected );
      actionForm.reset();
      } // end if

      // Prepare data for view tile
    context.putAttribute( "selected", selected );
    context.putAttribute( "catalog", catalog );

    if(debug)
      System.out.println("Exit action LayoutSettingAction");
    return null;
   }
}
