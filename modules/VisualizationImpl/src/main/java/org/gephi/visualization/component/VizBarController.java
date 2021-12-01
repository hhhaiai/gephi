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

package org.gephi.visualization.component;

import org.gephi.ui.components.JDropDownButton;
import org.gephi.visualization.VizController;
import org.openide.util.NbBundle;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author Mathieu Bastian
 */
public class VizBarController {

    private VizToolbarGroup[] groups;
    private VizToolbar toolbar;
    private VizExtendedBar extendedBar;

    public VizBarController() {
        createDefaultGroups();
    }

    private void createDefaultGroups() {
        groups = new VizToolbarGroup[4];

        groups[0] = new GlobalGroupBar();
        groups[1] = new NodeGroupBar();
        groups[2] = new EdgeGroupBar();
        groups[3] = new LabelGroupBar();

//        model.addPropertyChangeListener(new PropertyChangeListener() {
//            @Override
//            public void propertyChange(PropertyChangeEvent evt) {
//                if (evt.getPropertyName().equals("init")) {
//                    toolbar.setEnable(!model.isDefaultModel());
//                    ((NodeGroupBar) groups[1]).setModelValues();
//                    ((EdgeGroupBar) groups[2]).setModelValues();
//                    ((LabelGroupBar) groups[3]).setModelValues();
//                }
//            }
//        });
        //TODO
    }

    public VizToolbar getToolbar() {
        toolbar = new VizToolbar(groups);
//        toolbar.setEnable(!model.isDefaultModel());
        //TODO
        return toolbar;
    }

    public VizExtendedBar getExtendedBar() {
        extendedBar = new VizExtendedBar(groups);
        return extendedBar;
    }

    private static class GlobalGroupBar implements VizToolbarGroup {

        @Override
        public String getName() {
            return NbBundle.getMessage(VizBarController.class, "VizToolbar.Global.groupBarTitle");
        }

        @Override
        public JComponent[] getToolbarComponents() {
            JComponent[] components = new JComponent[1];

//            //Background color
//            VizModel vizModel = VizController.getInstance().getVizModel();
//            final JButton backgroundColorButton = new JColorBlackWhiteSwitcher(vizModel.getBackgroundColor());
//            backgroundColorButton
//                .setToolTipText(NbBundle.getMessage(VizBarController.class, "VizToolbar.Global.background"));
//            backgroundColorButton.addPropertyChangeListener(JColorButton.EVENT_COLOR, new PropertyChangeListener() {
//                @Override
//                public void propertyChange(PropertyChangeEvent evt) {
//                    VizModel vizModel = VizController.getInstance().getVizModel();
//                    Color backgroundColor = ((JColorBlackWhiteSwitcher) backgroundColorButton).getColor();
//                    vizModel.setBackgroundColor(backgroundColor);
//
//                    TextModelImpl textModel = VizController.getInstance().getVizModel().getTextModel();
//                    boolean isDarkBackground =
//                        (backgroundColor.getRed() + backgroundColor.getGreen() + backgroundColor.getBlue()) / 3 < 128;
//                    textModel.setNodeColor(isDarkBackground ? Color.WHITE : Color.BLACK);
//                }
//            });
//            vizModel.addPropertyChangeListener(new PropertyChangeListener() {
//                @Override
//                public void propertyChange(PropertyChangeEvent evt) {
//                    if (evt.getPropertyName().equals("backgroundColor")) {
//                        VizModel vizModel = VizController.getInstance().getVizModel();
//                        if (!(((JColorBlackWhiteSwitcher) backgroundColorButton).getColor())
//                            .equals(vizModel.getBackgroundColor())) {
//                            ((JColorBlackWhiteSwitcher) backgroundColorButton).setColor(vizModel.getBackgroundColor());
//                        }
//                    }
//                }
//            });
//            components[0] = backgroundColorButton;

            //TODO

            //Screenshots
            JPopupMenu screenshotPopup = new JPopupMenu();
            JMenuItem configureScreenshotItem =
                new JMenuItem(NbBundle.getMessage(VizBarController.class, "VizToolbar.Global.screenshot.configure"));
            configureScreenshotItem.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    VizController.getInstance().getScreenshotMaker().configure();
                }
            });
            screenshotPopup.add(configureScreenshotItem);
            final JButton screenshotButton = new JDropDownButton(
                new javax.swing.ImageIcon(getClass().getResource("/org/gephi/visualization/component/screenshot.png")),
                screenshotPopup);
            screenshotButton
                .setToolTipText(NbBundle.getMessage(VizBarController.class, "VizToolbar.Global.screenshot"));
            screenshotButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    VizController.getInstance().getScreenshotMaker().takeScreenshot();
                }
            });
            components[0] = screenshotButton;

            return components;
        }

        @Override
        public JComponent getExtendedComponent() {
            GlobalSettingsPanel panel = new GlobalSettingsPanel();
            panel.setup();
            return panel;
        }

        @Override
        public boolean hasToolbar() {
            return true;
        }

        @Override
        public boolean hasExtended() {
            return true;
        }
    }

    private static class NodeGroupBar implements VizToolbarGroup {

        JComponent[] components = new JComponent[1];

        @Override
        public String getName() {
            return NbBundle.getMessage(VizBarController.class, "VizToolbar.Nodes.groupBarTitle");
        }

        public void setModelValues() {
//            ((JToggleButton) components[0]).setSelected(vizModel.getTextModel().isShowNodeLabels());
            //TODO
        }

        @Override
        public JComponent[] getToolbarComponents() {
//            //Show labels buttons
//            VizModel vizModel = VizController.getInstance().getVizModel();
//            final JToggleButton showLabelsButton = new JToggleButton();
//            showLabelsButton.setSelected(vizModel.getTextModel().isShowNodeLabels());
//            showLabelsButton.setToolTipText(NbBundle.getMessage(VizBarController.class, "VizToolbar.Nodes.showLabels"));
//            showLabelsButton.setIcon(new javax.swing.ImageIcon(
//                getClass().getResource("/org/gephi/visualization/component/showNodeLabels.png")));
//            showLabelsButton.addActionListener(new ActionListener() {
//                @Override
//                public void actionPerformed(ActionEvent e) {
//                    VizModel vizModel = VizController.getInstance().getVizModel();
//                    vizModel.getTextModel().setShowNodeLabels(showLabelsButton.isSelected());
//                }
//            });
//            vizModel.getTextModel().addChangeListener(new ChangeListener() {
//                @Override
//                public void stateChanged(ChangeEvent e) {
//                    TextModelImpl textModel = VizController.getInstance().getVizModel().getTextModel();
//                    if (showLabelsButton.isSelected() != textModel.isShowNodeLabels()) {
//                        showLabelsButton.setSelected(textModel.isShowNodeLabels());
//                    }
//                }
//            });
//            components[0] = showLabelsButton;
//
//            return components;
            //TODO

            return new JComponent[0];
        }

        @Override
        public JComponent getExtendedComponent() {
//            NodeSettingsPanel panel = new NodeSettingsPanel();
//            panel.setup();
//            return panel;
            return null;
        }

        @Override
        public boolean hasToolbar() {
            return true;
        }

        @Override
        public boolean hasExtended() {
            return true;
        }
    }

    private static class EdgeGroupBar implements VizToolbarGroup {

        JComponent[] components = new JComponent[4];

        @Override
        public String getName() {
            return NbBundle.getMessage(VizBarController.class, "VizToolbar.Edges.groupBarTitle");
        }

        public void setModelValues() {
//            ((JToggleButton) components[2]).setSelected(vizModel.getTextModel().isShowEdgeLabels());
//            ((JSlider) components[3]).setValue((int) ((vizModel.getEdgeScale() - 0.1f) * 10));

            //TODO
        }

        @Override
        public JComponent[] getToolbarComponents() {
//            //Show edges buttons
//            VizModel vizModel = VizController.getInstance().getVizModel();
//            final JToggleButton showEdgeButton = new JToggleButton();
//            showEdgeButton.setSelected(vizModel.isShowEdges());
//            showEdgeButton.setToolTipText(NbBundle.getMessage(VizBarController.class, "VizToolbar.Edges.showEdges"));
//            showEdgeButton.setIcon(
//                new javax.swing.ImageIcon(getClass().getResource("/org/gephi/visualization/component/showEdges.png")));
//            showEdgeButton.addActionListener(new ActionListener() {
//                @Override
//                public void actionPerformed(ActionEvent e) {
//                    VizModel vizModel = VizController.getInstance().getVizModel();
//                    vizModel.setShowEdges(showEdgeButton.isSelected());
//                }
//            });
//            vizModel.addPropertyChangeListener(new PropertyChangeListener() {
//                @Override
//                public void propertyChange(PropertyChangeEvent evt) {
//                    if (evt.getPropertyName().equals("showEdges")) {
//                        VizModel vizModel = VizController.getInstance().getVizModel();
//                        if (showEdgeButton.isSelected() != vizModel.isShowEdges()) {
//                            showEdgeButton.setSelected(vizModel.isShowEdges());
//                        }
//                    }
//                }
//            });
//            components[0] = showEdgeButton;
//
//            //Edge color mode
//            final JToggleButton edgeHasNodeColorButton = new JToggleButton();
//            edgeHasNodeColorButton.setSelected(!vizModel.isEdgeHasUniColor());
//            edgeHasNodeColorButton
//                .setToolTipText(NbBundle.getMessage(VizBarController.class, "VizToolbar.Edges.edgeNodeColor"));
//            edgeHasNodeColorButton.setIcon(new javax.swing.ImageIcon(
//                getClass().getResource("/org/gephi/visualization/component/edgeNodeColor.png")));
//            edgeHasNodeColorButton.addActionListener(new ActionListener() {
//                @Override
//                public void actionPerformed(ActionEvent e) {
//                    VizModel vizModel = VizController.getInstance().getVizModel();
//                    vizModel.setEdgeHasUniColor(!edgeHasNodeColorButton.isSelected());
//                }
//            });
//            vizModel.addPropertyChangeListener(new PropertyChangeListener() {
//                @Override
//                public void propertyChange(PropertyChangeEvent evt) {
//                    if (evt.getPropertyName().equals("edgeHasUniColor")) {
//                        VizModel vizModel = VizController.getInstance().getVizModel();
//                        if (edgeHasNodeColorButton.isSelected() != !vizModel.isEdgeHasUniColor()) {
//                            edgeHasNodeColorButton.setSelected(!vizModel.isEdgeHasUniColor());
//                        }
//                    }
//                }
//            });
//            components[1] = edgeHasNodeColorButton;
//
//            //Show labels buttons
//            final JToggleButton showLabelsButton = new JToggleButton();
//            showLabelsButton.setSelected(vizModel.getTextModel().isShowEdgeLabels());
//            showLabelsButton.setToolTipText(NbBundle.getMessage(VizBarController.class, "VizToolbar.Edges.showLabels"));
//            showLabelsButton.setIcon(new javax.swing.ImageIcon(
//                getClass().getResource("/org/gephi/visualization/component/showEdgeLabels.png")));
//            showLabelsButton.addActionListener(new ActionListener() {
//                @Override
//                public void actionPerformed(ActionEvent e) {
//                    VizModel vizModel = VizController.getInstance().getVizModel();
//                    vizModel.getTextModel().setShowEdgeLabels(showLabelsButton.isSelected());
//                }
//            });
//            vizModel.getTextModel().addChangeListener(new ChangeListener() {
//                @Override
//                public void stateChanged(ChangeEvent e) {
//                    TextModelImpl textModel = VizController.getInstance().getVizModel().getTextModel();
//                    if (showLabelsButton.isSelected() != textModel.isShowEdgeLabels()) {
//                        showLabelsButton.setSelected(textModel.isShowEdgeLabels());
//                    }
//                }
//            });
//            components[2] = showLabelsButton;
//
//            //EdgeScale slider
//            final JSlider edgeScaleSlider = new JSlider(0, 100, (int) ((vizModel.getEdgeScale() - 0.1f) * 10));
//            edgeScaleSlider.setToolTipText(NbBundle.getMessage(VizBarController.class, "VizToolbar.Edges.edgeScale"));
//            edgeScaleSlider.addChangeListener(new ChangeListener() {
//                @Override
//                public void stateChanged(ChangeEvent e) {
//                    VizModel vizModel = VizController.getInstance().getVizModel();
//                    if (vizModel.getEdgeScale() != (edgeScaleSlider.getValue() / 10f + 0.1f)) {
//                        vizModel.setEdgeScale(edgeScaleSlider.getValue() / 10f + 0.1f);
//                    }
//                }
//            });
//            edgeScaleSlider.setPreferredSize(new Dimension(100, 20));
//            edgeScaleSlider.setMaximumSize(new Dimension(100, 20));
//            vizModel.addPropertyChangeListener(new PropertyChangeListener() {
//                @Override
//                public void propertyChange(PropertyChangeEvent evt) {
//                    if (evt.getPropertyName().equals("edgeScale")) {
//                        VizModel vizModel = VizController.getInstance().getVizModel();
//                        if (vizModel.getEdgeScale() != (edgeScaleSlider.getValue() / 10f + 0.1f)) {
//                            edgeScaleSlider.setValue((int) ((vizModel.getEdgeScale() - 0.1f) * 10));
//                        }
//                    }
//                }
//            });
//            components[3] = edgeScaleSlider;
//            return components;

            //TODO

            return new JComponent[0];
        }

        @Override
        public JComponent getExtendedComponent() {
            EdgeSettingsPanel panel = new EdgeSettingsPanel();
            panel.setup();
            return panel;
        }

        @Override
        public boolean hasToolbar() {
            return true;
        }

        @Override
        public boolean hasExtended() {
            return true;
        }
    }

    private static class LabelGroupBar implements VizToolbarGroup {

        JComponent[] components = new JComponent[6];

        @Override
        public String getName() {
            return NbBundle.getMessage(VizBarController.class, "VizToolbar.Labels.groupBarTitle");
        }

        public void setModelValues() {
//            TextModelImpl model = vizModel.getTextModel();
//            ((JPopupButton) components[0]).setSelectedItem(model.getSizeMode());
//            ((JPopupButton) components[1]).setSelectedItem(model.getColorMode());
//            ((JButton) components[2]).setText(model.getNodeFont().getFontName() + ", " + model.getNodeFont().getSize());
//            ((JSlider) components[3]).setValue((int) (model.getNodeSizeFactor() * 100f));

            //TODO
        }

        @Override
        public JComponent[] getToolbarComponents() {
//            TextModelImpl model = VizController.getInstance().getVizModel().getTextModel();
//
//            //Mode
//            final JPopupButton labelSizeModeButton = new JPopupButton();
//            TextManager textManager = VizController.getInstance().getTextManager();
//            for (final SizeMode sm : textManager.getSizeModes()) {
//                labelSizeModeButton.addItem(sm, sm.getIcon());
//            }
//            labelSizeModeButton.setSelectedItem(model.getSizeMode());
//            labelSizeModeButton.setChangeListener(new ChangeListener() {
//                @Override
//                public void stateChanged(ChangeEvent e) {
//                    SizeMode sm = (SizeMode) e.getSource();
//                    TextModelImpl model = VizController.getInstance().getVizModel().getTextModel();
//                    model.setSizeMode(sm);
//                }
//            });
//            labelSizeModeButton.setIcon(new javax.swing.ImageIcon(
//                getClass().getResource("/org/gephi/visualization/component/labelSizeMode.png")));
//            labelSizeModeButton
//                .setToolTipText(NbBundle.getMessage(VizBarController.class, "VizToolbar.Labels.sizeMode"));
//            model.addChangeListener(new ChangeListener() {
//                @Override
//                public void stateChanged(ChangeEvent e) {
//                    TextModelImpl model = VizController.getInstance().getVizModel().getTextModel();
//                    if (model.getSizeMode() != labelSizeModeButton.getSelectedItem()) {
//                        labelSizeModeButton.setSelectedItem(model.getSizeMode());
//                    }
//                }
//            });
//            components[0] = labelSizeModeButton;
//
//            //Color mode
//            final JPopupButton labelColorModeButton = new JPopupButton();
//            for (final ColorMode cm : textManager.getColorModes()) {
//                labelColorModeButton.addItem(cm, cm.getIcon());
//            }
//            labelColorModeButton.setSelectedItem(textManager.getModel().getColorMode());
//            labelColorModeButton.setChangeListener(new ChangeListener() {
//                @Override
//                public void stateChanged(ChangeEvent e) {
//                    ColorMode cm = (ColorMode) e.getSource();
//                    TextModelImpl model = VizController.getInstance().getVizModel().getTextModel();
//                    model.setColorMode(cm);
//                }
//            });
//            labelColorModeButton.setIcon(new javax.swing.ImageIcon(
//                getClass().getResource("/org/gephi/visualization/component/labelColorMode.png")));
//            labelColorModeButton
//                .setToolTipText(NbBundle.getMessage(VizBarController.class, "VizToolbar.Labels.colorMode"));
//            model.addChangeListener(new ChangeListener() {
//                @Override
//                public void stateChanged(ChangeEvent e) {
//                    TextModelImpl model = VizController.getInstance().getVizModel().getTextModel();
//                    if (model.getColorMode() != labelColorModeButton.getSelectedItem()) {
//                        labelColorModeButton.setSelectedItem(model.getColorMode());
//                    }
//                }
//            });
//            components[1] = labelColorModeButton;
//
//            //Font
//            final JButton fontButton =
//                new JButton(model.getNodeFont().getFontName() + ", " + model.getNodeFont().getSize());
//            fontButton.setToolTipText(NbBundle.getMessage(VizBarController.class, "VizToolbar.Labels.font"));
//            fontButton.addActionListener(new ActionListener() {
//                @Override
//                public void actionPerformed(ActionEvent e) {
//                    TextModelImpl model = VizController.getInstance().getVizModel().getTextModel();
//                    Font font =
//                        JFontChooser.showDialog(WindowManager.getDefault().getMainWindow(), model.getNodeFont());
//                    if (font != null && font != model.getNodeFont()) {
//                        model.setNodeFont(font);
//                    }
//                }
//            });
//            model.addChangeListener(new ChangeListener() {
//                @Override
//                public void stateChanged(ChangeEvent e) {
//                    TextModelImpl model = VizController.getInstance().getVizModel().getTextModel();
//                    fontButton.setText(model.getNodeFont().getFontName() + ", " + model.getNodeFont().getSize());
//                }
//            });
//            components[2] = fontButton;
//
//            //Font size
//            final JSlider fontSizeSlider = new JSlider(0, 100, (int) (model.getNodeSizeFactor() * 100f));
//            fontSizeSlider.setToolTipText(NbBundle.getMessage(VizBarController.class, "VizToolbar.Labels.fontScale"));
//            fontSizeSlider.addChangeListener(new ChangeListener() {
//                @Override
//                public void stateChanged(ChangeEvent e) {
//                    TextModelImpl model = VizController.getInstance().getVizModel().getTextModel();
//                    model.setNodeSizeFactor(fontSizeSlider.getValue() / 100f);
//                }
//            });
//            fontSizeSlider.setPreferredSize(new Dimension(100, 20));
//            fontSizeSlider.setMaximumSize(new Dimension(100, 20));
//            model.addChangeListener(new ChangeListener() {
//                @Override
//                public void stateChanged(ChangeEvent e) {
//                    TextModelImpl model = VizController.getInstance().getVizModel().getTextModel();
//                    if (((int) (model.getNodeSizeFactor() * 100f)) != fontSizeSlider.getValue()) {
//                        fontSizeSlider.setValue((int) (model.getNodeSizeFactor() * 100f));
//                    }
//                }
//            });
//            components[3] = fontSizeSlider;
//
//            //Color
//            final ColorChooser colorChooser = new ColorChooser(model.getNodeColor());
//            colorChooser.setToolTipText(NbBundle.getMessage(VizBarController.class, "VizToolbar.Labels.defaultColor"));
//            colorChooser.setPreferredSize(new Dimension(16, 16));
//            colorChooser.setMaximumSize(new Dimension(16, 16));
//            colorChooser.addPropertyChangeListener(new PropertyChangeListener() {
//                @Override
//                public void propertyChange(PropertyChangeEvent evt) {
//                    if (evt.getPropertyName().equals(ColorChooser.PROP_COLOR)) {
//                        TextModelImpl model = VizController.getInstance().getVizModel().getTextModel();
//                        model.setNodeColor(colorChooser.getColor());
//                    }
//                }
//            });
//            model.addChangeListener(new ChangeListener() {
//                @Override
//                public void stateChanged(ChangeEvent e) {
//                    TextModelImpl model = VizController.getInstance().getVizModel().getTextModel();
//                    if (!model.getNodeColor().equals(colorChooser.getColor())) {
//                        colorChooser.setColor(model.getNodeColor());
//                    }
//                }
//            });
//            components[4] = colorChooser;
//
//            //Attributes
//            final JButton attributesButton = new JButton();
//            attributesButton.setIcon(new javax.swing.ImageIcon(
//                getClass().getResource("/org/gephi/visualization/component/configureLabels.png")));
//            attributesButton
//                .setToolTipText(NbBundle.getMessage(VizBarController.class, "VizToolbar.Labels.attributes"));
//            attributesButton.addActionListener(new ActionListener() {
//                @Override
//                public void actionPerformed(ActionEvent e) {
//                    TextModelImpl model = VizController.getInstance().getVizModel().getTextModel();
//                    LabelAttributesPanel panel = new LabelAttributesPanel();
//                    panel.setup(model);
//                    DialogDescriptor dd = new DialogDescriptor(panel,
//                        NbBundle.getMessage(VizBarController.class, "LabelAttributesPanel.title"), true,
//                        NotifyDescriptor.OK_CANCEL_OPTION, null, null);
//                    if (DialogDisplayer.getDefault().notify(dd).equals(NotifyDescriptor.OK_OPTION)) {
//                        panel.unsetup();
//                        return;
//                    }
//                }
//            });
//            components[5] = attributesButton;
//
//            return components;
            //TODO
            return new JComponent[0];
        }

        @Override
        public JComponent getExtendedComponent() {
            LabelSettingsPanel panel = new LabelSettingsPanel();
            panel.setup();
            return panel;
        }

        @Override
        public boolean hasToolbar() {
            return true;
        }

        @Override
        public boolean hasExtended() {
            return true;
        }
    }
}
