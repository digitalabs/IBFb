package ibfb.nursery.advance;

import ibfb.nursery.core.NurseryEditorTopComponent;
import java.util.ArrayList;

public class MethodsClass {
    public static final int CIMMYT_WHEAT = 0;
    public static final int CIMMYT_MAIZE = 1;
    public static final int OTHER_CROPS = 2;
    
    int convention = 0;  //0=CIMMYT WHEAT,  1= CIMMYT MAIZE,   2=OTHER
    
    /**
     * Suffix to add 
     */
    private String suffix;

    public void setConvention(int conv) {
        this.convention = conv;
    }

    public ArrayList<String> giveMeDataDerivativeOld(String seed, int samples) {
        ArrayList<String> data = new ArrayList<String>();
        String newSeed = "";

        switch (convention) {
            case CIMMYT_WHEAT:
                
                if (samples == 0) {
                    newSeed = seed + "-" + samples + suffix;
                    data.add(newSeed);
                }

                if (samples < 0) {
                    
                    
                    if(seed.endsWith("T")){
                        
                   // newSeed =  "0" + Math.abs(samples)+"TOP" + suffix;
                    newSeed = seed + "-0" + Math.abs(samples)+"TOP" + suffix;

                    data.add(newSeed);   
                    }else{
                    
                    newSeed = seed + "-0" + Math.abs(samples) + suffix;
                    data.add(newSeed);
                    }
                    
                    
                }


                if (samples > 0) {
                    for (int i = 0; i < samples; i++) {
                        newSeed = seed + "-" + (i + 1) + suffix;
                        data.add(newSeed);
                    }
                }

                break;

            case CIMMYT_MAIZE:

                newSeed = seed + "-" + samples;
                data.add(newSeed);


                break;


            case OTHER_CROPS:

                for (int i = 0; i < samples; i++) {
                    newSeed = seed + "-" + (i + 1)+ suffix;
                    data.add(newSeed);
                }


                break;

        }


        return data;
    }

    //GCP-7193
    public ArrayList<NurseryEditorTopComponent.WheatGermplasmData> giveMeDataDerivativeForWheat(String seed, int samples, boolean isBulk) {
        ArrayList<NurseryEditorTopComponent.WheatGermplasmData> data = new ArrayList<NurseryEditorTopComponent.WheatGermplasmData>();
        String newSeed = "";

        //GCP-7193 (c)
        if (seed != null && seed.toUpperCase().startsWith("LOCAL CHECK")) {
            //do nothing, return empty list
            return data;
        }
        
        //GCP-7193 (a)
        if (samples == 0) {
            newSeed = seed + "-" + samples + suffix;
            //data.add(newSeed);
            data.add(new NurseryEditorTopComponent.WheatGermplasmData(newSeed, samples));
        }
        

        if (samples < 0) {


            if(seed.endsWith("T")){

                // newSeed =  "0" + Math.abs(samples)+"TOP" + suffix;
                 newSeed = seed + "-0" + Math.abs(samples)+"TOP" + suffix;

                 //data.add(newSeed);   
                 data.add(new NurseryEditorTopComponent.WheatGermplasmData(newSeed, Integer.valueOf(samples)));
            }else{

                newSeed = seed + "-0" + Math.abs(samples) + suffix;
                //data.add(newSeed);
                data.add(new NurseryEditorTopComponent.WheatGermplasmData(newSeed, samples));
            }


        }


        if (samples > 0) {
            if (isBulk) {
                 newSeed = seed + "-" + suffix;
                 //data.add(newSeed);
                 data.add(new NurseryEditorTopComponent.WheatGermplasmData(newSeed, Integer.valueOf(samples)));
            }
            else {
                for (int i = 0; i < samples; i++) {
                    newSeed = seed + "-" + (i + 1) + suffix;
                    //data.add(newSeed);
                    data.add(new NurseryEditorTopComponent.WheatGermplasmData(newSeed, Integer.valueOf(samples)));
                }
            }
        }
        return data;
    }
    
    public ArrayList<String> giveMeDataDerivative(String seed, int samples, boolean isBulk) {
        ArrayList<String> data = new ArrayList<String>();
        String newSeed = "";

        //GCP-7193 (c)
        if (seed != null && seed.toUpperCase().startsWith("LOCAL CHECK")) {
            //do nothing, return empty list
            return data;
        }
        
        switch (convention) {
            case CIMMYT_WHEAT:

                //GCP-7193
                
                if (samples == 0) {
                    newSeed = seed + "-" + samples + suffix;
                    data.add(newSeed);
                }
                

                if (samples < 0) {
                    
                    
                    if(seed.endsWith("T")){
                        
                   // newSeed =  "0" + Math.abs(samples)+"TOP" + suffix;
                    newSeed = seed + "-0" + Math.abs(samples)+"TOP" + suffix;

                    data.add(newSeed);   
                    }else{
                    
                    newSeed = seed + "-0" + Math.abs(samples) + suffix;
                    data.add(newSeed);
                    }
                    
                    
                }


                if (samples > 0) {
                    if (isBulk) {
                        newSeed = seed + "-" + suffix;
                        data.add(newSeed);
                    }
                    else {
                        for (int i = 0; i < samples; i++) {
                            newSeed = seed + "-" + (i + 1) + suffix;
                            data.add(newSeed);
                        }
                    }
                }

                break;

            case CIMMYT_MAIZE:

                //GCP-7193 (a)
                if (samples > 0) {
                    newSeed = seed + "-" + samples;
                    data.add(newSeed);
                }

                break;


            case OTHER_CROPS:
                //GCP-7193 (a)
                if (samples > 0) {
                    if (isBulk) {
                        newSeed = seed + "-" + suffix;
                        data.add(newSeed);
                    }
                    else {
                        for (int i = 0; i < samples; i++) {
                            newSeed = seed + "-" + (i + 1)+ suffix;
                            data.add(newSeed);
                        }
                    }
                }

                break;

        }


        return data;
    }

    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }
    
    
     
}
