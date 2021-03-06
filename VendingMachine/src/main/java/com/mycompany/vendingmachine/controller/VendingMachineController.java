package com.mycompany.vendingmachine.controller;

import com.mycompany.vendingmachine.dao.VendingMachinePersistenceException;
import com.mycompany.vendingmachine.service.Change;
import com.mycompany.vendingmachine.service.InsufficientFundsException;
import com.mycompany.vendingmachine.service.NoItemInventoryException;
import com.mycompany.vendingmachine.ui.VendingMachineView;
import com.mycompany.vendingmachine.service.VendingMachineServiceLayer;

/**
 *
 * @author n0252282
 */
public class VendingMachineController {

    VendingMachineView view;

    private VendingMachineServiceLayer service;

    public VendingMachineController(VendingMachineView view, VendingMachineServiceLayer service) {
        this.view = view;
        this.service = service;

    }

    public void run() {
        boolean keepGoing = true;
        int menuSelection = 0;
        try {
            while (keepGoing) {

                menuSelection = getMenuSelection();

                switch (menuSelection) {
                    case 1:
                        addMoney();
                        break;
                    case 2:
                        purchase();
                        break;
                    case 3:
                        giveChange();
                        break;
                    case 4:
                        keepGoing = false;
                        break;
                    default:
                        unknownCommand();
                }

            }
            exitMessage();
        } catch (VendingMachinePersistenceException | InsufficientFundsException | NoItemInventoryException e) {
            view.displayErrorMessage(e.getMessage());
        }
    }

    private int getMenuSelection() {

        try {
            view.displayAllItems(service.getAllItemsFiltered());
        } catch (VendingMachinePersistenceException e) {
            view.displayErrorMessage(e.getMessage());
        }
        return view.printMenuAndGetSelection(service.getCurrentMoney());
    }

    private void addMoney() {
        service.setCurrentMoney(view.getMoneyEntry());
        view.displayCurrentMoney(service.getCurrentMoney());
    }

    private void purchase() throws VendingMachinePersistenceException, InsufficientFundsException, NoItemInventoryException {
        Boolean success = false;
        do {
            try {
                Change change = service.purchaseItem(view.getItemChoice());
                view.displayChange(change);
                view.displayPurchaseSuccess();
                success = true;
            } catch (VendingMachinePersistenceException | InsufficientFundsException | NoItemInventoryException e) {
                view.displayErrorMessage(e.getMessage());
                continue;
            }

        } while (false);

    }

    private void giveChange() throws VendingMachinePersistenceException {
        view.displayChange(service.cancelGiveChange());
    }

    private void unknownCommand() {
        view.displayUnknownCommandBanner();
    }

    private void exitMessage() {
        view.displayExitBanner();
    }

}
