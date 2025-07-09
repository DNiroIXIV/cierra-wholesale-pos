/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package thogakade.main;

import java.awt.BorderLayout;
import javax.swing.JPanel;
import thogakade.view.dashboard.CrudPanel;
import thogakade.view.dashboard.DashboardForm;

/**
 *
 * @author Nirodha
 */
public class Main {
    public static void main(String[] args) {
        DashboardForm dashboardForm = new DashboardForm();
                CrudPanel crudPanel = new CrudPanel();
                JPanel dynamicPanel = dashboardForm.getCenterPanel();
                dynamicPanel.add(crudPanel, BorderLayout.CENTER);
                dashboardForm.setVisible(true);
                dashboardForm.setLocationRelativeTo(null);
    }
}
