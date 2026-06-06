abstract class ModelTrainer {
    public final void trainPipeline(String dataPath){
        loadData(dataPath);
        preprocessData();
        trainModel();
        evaluateModel();
        saveModel();
    }

    protected void loadData(String path){
        System.out.println("[Common] Loading dataset from " + path);
    }

    protected void preprocessData(){
        System.out.println("[Common] Preprocessing dataset");
    }

    protected abstract void trainModel();

    protected abstract void evaluateModel();

    protected void saveModel(){
        System.out.println("[Common] Saving model to disk");
    }
}

class NeuralNetworkTrainer extends ModelTrainer {
    @Override 
    protected void trainModel(){
        System.out.println("[Neural Network] Training model");
    }

    @Override 
    protected void evaluateModel(){
        System.out.println("[Neural Network] Evaluating model");
    }

    @Override 
    protected void saveModel(){
        System.out.println("[Neural Network] Saving model to disk");
    }
}

class DecisionTreeTrainer extends ModelTrainer {
    @Override 
    protected void trainModel(){
        System.out.println("[Decision Tree] Training model");
    }

    @Override 
    protected void evaluateModel(){
        System.out.println("[Decision Tree] Evaluating model");
    }
}

public class TemplateMethodPattern {
    public static void main(String[] args) {
        NeuralNetworkTrainer trainer = new NeuralNetworkTrainer();
        trainer.trainPipeline("data.csv");

        ModelTrainer trainer2 = new DecisionTreeTrainer();
        trainer2.trainPipeline("data.csv");
    }
}