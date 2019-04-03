package lt.vu.mif.usecases;

import lt.vu.mif.services.IntensiveCalculator;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

@ApplicationScoped
@Named
public class Calculate implements Serializable {
    @Inject
    private IntensiveCalculator intensiveCalculator;

    private Future<Integer> calculationTask = null;

    public String calculate() {
        Map<String, String> requestParameters =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        this.calculationTask = intensiveCalculator.calculateIntensively();
        return  "/calculate.xhtml?faces-redirect=true";
    }

    public boolean isCalculationRunning() {
        return this.calculationTask != null && !calculationTask.isDone();
    }

    public String getCalculationStatus() throws ExecutionException, InterruptedException {
        if (this.calculationTask == null) {
            return null;
        } else if (this.isCalculationRunning()) {
            return "Calculation in progress";
        }
        return "Calculation result: " + this.calculationTask.get();
    }


}
