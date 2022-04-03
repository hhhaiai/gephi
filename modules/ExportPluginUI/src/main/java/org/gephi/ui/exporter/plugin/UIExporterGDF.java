/*
Copyright 2008-2010 Gephi
Authors : Mathieu Bastian <mathieu.bastian@gephi.org>
Website : http://www.gephi.org

This file is part of Gephi.

DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.

Copyright 2011 Gephi Consortium. All rights reserved.

The contents of this file are subject to the terms of either the GNU
General Public License Version 3 only ("GPL") or the Common
Development and Distribution License("CDDL") (collectively, the
"License"). You may not use this file except in compliance with the
License. You can obtain a copy of the License at
http://gephi.org/about/legal/license-notice/
or /cddl-1.0.txt and /gpl-3.0.txt. See the License for the
specific language governing permissions and limitations under the
License.  When distributing the software, include this License Header
Notice in each file and include the License files at
/cddl-1.0.txt and /gpl-3.0.txt. If applicable, add the following below the
License Header, with the fields enclosed by brackets [] replaced by
your own identifying information:
"Portions Copyrighted [year] [name of copyright owner]"

If you wish your version of this file to be governed by only the CDDL
or only the GPL Version 3, indicate your decision by adding
"[Contributor] elects to include this software in this distribution
under the [CDDL or GPL Version 3] license." If you do not indicate a
single choice of license, a recipient has the option to distribute
your version of this file under either the CDDL, the GPL Version 3 or
to extend the choice of license to its licensees as provided above.
However, if you add GPL Version 3 code and therefore, elected the GPL
Version 3 license, then the option applies only if the new code is
made subject to such option by the copyright holder.

Contributor(s):

Portions Copyrighted 2011 Gephi Consortium.
*/

package org.gephi.ui.exporter.plugin;

import javax.swing.JPanel;
import org.gephi.io.exporter.plugin.ExporterGDF;
import org.gephi.io.exporter.spi.Exporter;
import org.gephi.io.exporter.spi.ExporterUI;
import org.openide.util.NbBundle;
import org.openide.util.lookup.ServiceProvider;

/**
 * @author Mathieu Bastian
 */
@ServiceProvider(service = ExporterUI.class)
public class UIExporterGDF implements ExporterUI {

    private final ExporterGDFSettings settings = new ExporterGDFSettings();
    private UIExporterGDFPanel panel;
    private ExporterGDF exporterGDF;

    @Override
    public void setup(Exporter exporter) {
        exporterGDF = (ExporterGDF) exporter;
        settings.load(exporterGDF);
        panel.setup(exporterGDF);
    }

    @Override
    public void unsetup(boolean update) {
        if (update) {
            panel.unsetup(exporterGDF);
            settings.save(exporterGDF);
        }
        panel = null;
        exporterGDF = null;
    }

    @Override
    public JPanel getPanel() {
        panel = new UIExporterGDFPanel();
        return panel;
    }

    @Override
    public boolean isUIForExporter(Exporter exporter) {
        return exporter instanceof ExporterGDF;
    }

    @Override
    public String getDisplayName() {
        return NbBundle.getMessage(UIExporterGDF.class, "UIExporterGDF.name");
    }

    private static class ExporterGDFSettings extends AbstractExporterSettings {

        // Preference names
        private final static String NORMALIZE = "GDF_normalize";
        private final static String SIMPLE_QUOTES = "GDF_simpleQuotes";
        private final static String USE_QUOTES = "GDF_useQuotes";
        private final static String EXPORT_COLORS = "GDF_exportColors";
        private final static String EXPORT_POSITION = "GDF_exportPosition";
        private final static String EXPORT_ATTRIBUTES = "GDF_exportAttributes";
        private final static String EXPORT_VISIBILITY = "GDF_exportVisibility";
        // Default
        private final static ExporterGDF DEFAULT = new ExporterGDF();

        private void save(ExporterGDF exporterGDF) {
            put(NORMALIZE, exporterGDF.isNormalize());
            put(SIMPLE_QUOTES, exporterGDF.isSimpleQuotes());
            put(USE_QUOTES, exporterGDF.isUseQuotes());
            put(EXPORT_COLORS, exporterGDF.isExportColors());
            put(EXPORT_POSITION, exporterGDF.isExportPosition());
            put(EXPORT_ATTRIBUTES, exporterGDF.isExportAttributes());
            put(EXPORT_VISIBILITY, exporterGDF.isExportVisibility());
        }

        private void load(ExporterGDF exporterGDF) {
            exporterGDF.setNormalize(get(NORMALIZE, DEFAULT.isNormalize()));
            exporterGDF.setSimpleQuotes(get(SIMPLE_QUOTES, DEFAULT.isSimpleQuotes()));
            exporterGDF.setUseQuotes(get(USE_QUOTES, DEFAULT.isUseQuotes()));
            exporterGDF.setExportColors(get(EXPORT_COLORS, DEFAULT.isExportColors()));
            exporterGDF.setExportAttributes(get(EXPORT_ATTRIBUTES, DEFAULT.isExportAttributes()));
            exporterGDF.setExportPosition(get(EXPORT_POSITION, DEFAULT.isExportPosition()));
            exporterGDF.setExportVisibility(get(EXPORT_VISIBILITY, DEFAULT.isExportVisibility()));
        }
    }
}
