package de.atlas.gui.presenter;


import de.atlas.gui.Euro2DollarRechnerView;
import de.atlas.model.Euro2DollarRechner;

public class Euro2DollarPresenterImpl implements Euro2DollarPresenter {
	
	private Euro2DollarRechnerView view;
	private Euro2DollarRechner model;
	
	
	
	/* (non-Javadoc)
	 * @see de.gui.presenter.IEuro2DollarPresenter#getView()
	 */
	@Override
	public Euro2DollarRechnerView getView() {
		return view;
	}

	/* (non-Javadoc)
	 * @see de.gui.presenter.IEuro2DollarPresenter#setView(de.gui.IEuro2DollarRechnerView)
	 */
	@Override
	public void setView(Euro2DollarRechnerView view) {
		this.view = view;
	}

	/* (non-Javadoc)
	 * @see de.gui.presenter.IEuro2DollarPresenter#getModel()
	 */
	@Override
	public Euro2DollarRechner getModel() {
		return model;
	}

	/* (non-Javadoc)
	 * @see de.gui.presenter.IEuro2DollarPresenter#setModel(de.model.IEuro2DollarRechner)
	 */
	@Override
	public void setModel(Euro2DollarRechner model) {
		this.model = model;
	}

	/* (non-Javadoc)
	 * @see de.gui.presenter.IEuro2DollarPresenter#rechnen()
	 */
	@Override
	public void onRechnen() {
		try {

			double dollar = model.calculateEuro2Dollar(Double.valueOf(view.getEuro()));
			view.setDollar(String.format("%.2f",dollar));
		} catch (NumberFormatException e) {
			view.setDollar("Keine Zahl.");
		}catch (NullPointerException e) {
			view.setDollar("Wert darf nicht null sein.");
		}catch (RuntimeException e) {
			view.setDollar("Ein Fehler ist aufgetreten");
		}


	}
	
	/* (non-Javadoc)
	 * @see de.gui.presenter.IEuro2DollarPresenter#beenden()
	 */
	@Override
	public void onBeenden() {  
		view.close();
	}
	
	/* (non-Javadoc)
	 * @see de.gui.presenter.IEuro2DollarPresenter#populateItems()
	 */
	@Override
	public void onPopulateItems() {
		view.setEuro("0");
		view.setDollar("0");
		view.setRechnenEnabled(true);

	}

	@Override
	public void updateRechnenActionState() {
		try {
			Double.valueOf(view.getEuro());
			view.setRechnenEnabled(true);
		} catch (Exception e) {
			view.setRechnenEnabled(false);
		}
	}

}
