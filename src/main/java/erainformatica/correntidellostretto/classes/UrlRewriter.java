/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package erainformatica.correntidellostretto.classes;

import javax.servlet.ServletContext;
import org.ocpsoft.rewrite.annotation.RewriteConfiguration;
import org.ocpsoft.rewrite.config.Configuration;
import org.ocpsoft.rewrite.config.ConfigurationBuilder;
import org.ocpsoft.rewrite.servlet.config.HttpConfigurationProvider;
import org.ocpsoft.rewrite.servlet.config.rule.Join;

/**
 *
 * @author robertodedomenico
 */
@RewriteConfiguration
public class UrlRewriter extends HttpConfigurationProvider
{
   @Override
   public int priority()
   {
     return 10;
   }

   @Override
   public Configuration getConfiguration(final ServletContext context)
   {
     return ConfigurationBuilder.begin()
         .addRule(Join.path("/tavola/messina/{mese}/{anno}").to("/index.xhtml?mese={mese}&anno={anno}"))
             .addRule(Join.path("/tabella/messina/{mese}/{anno}").to("/index.xhtml?mese={mese}&anno={anno}"))
             .addRule(Join.path("/tabella/{anno}").to("/index.xhtml?anno={anno}"))
             .addRule(Join.path("/tavola/{anno}").to("/index.xhtml?anno={anno}"))
             .addRule(Join.path("/messina/{anno}").to("/index.xhtml?anno={anno}"));

    }
}