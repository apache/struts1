package org.apache.struts.chain.legacy;

/**
 * Permissive version of TilesPlugin which doesn't mess with processor class.
 * Completely extends TilesPlugIn except for overriding protected method
 * <code>initRequestProcessorClass(ModuleConfig)</code> to a no-op.
 * Note class name capitalization consistent with org.apache.struts.tiles.TilesPlugin
 * rather than most other Struts plug-ins to avoid snagging folks who quickly
 * edit their struts-config.
 */
public class TilesPlugin extends org.apache.struts.tiles.TilesPlugin
{
    protected void initRequestProcessorClass(org.apache.struts.config.ModuleConfig config)
    {
        log.debug("Chain version of TilesPlugIn doesn't care what RequestProcessor you use!");
    }
}