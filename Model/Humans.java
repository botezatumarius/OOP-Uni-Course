package Model;
import java.util.Random;
public class Humans extends Organisms{
    private float foodProduced=0;

    public void produceFood(){
        if (amount>0)
            foodProduced+=0.001*amount*Ground.soilFertility;
        if (Ground.soilFertility<0||Ground.soilFertility==0){
            Ground.soilFertility=0;
            foodProduced-=0.001*amount;
            if (foodProduced<0)
                foodProduced=0;
                //amount=0;
        }
    }

    public void reproduce(int g){
        int amount1=amount/g;
        for (int i=0;i<amount1;i++){  //reproduce when soil fertility = 0
            Random rd = new Random(); 
            if (rd.nextBoolean()==true)
                amount+=1; 
        }
    }

    public float getFoodProduced(){
        if (foodProduced==0)
            amount=0;
        return foodProduced;
    }
    
    public void affectEnvironment(){
        World.temperature+=0.01*amount/1000*0.1/intelligence;  //global warming
        World.humidity-=0.02*amount/1000*0.1/intelligence;
        if (World.humidity<0)
            World.humidity=0;
            }

    public int wars(int weaponamount,float weapondestruction){
        if (amount>2&&evilness>0){
            amount-=0.001*evilness*amount*0.1+0.01*(weaponamount*weapondestruction); //kill each other
            intelligence-=0.0001*evilness*amount*0.001+0.0001*(weaponamount*weapondestruction)/intelligence;
        }
            return (int)(0.001*evilness*amount*0.1+0.01*(weaponamount*weapondestruction));
    }

    public void makeSoilFertile(){
        Ground.soilFertility+=0.0001*amount;
        if (Ground.soilFertility>=100)
            Ground.soilFertility=100;
    }

    public float getIntelligence(){
        return intelligence;
    }

    public void becomeSmarter(){
        intelligence+=0.001*amount;
        if (Ground.waterAmount>=60&&intelligence>1000){
            if (0.00001*amount*intelligence<=50)
                Ground.waterAmount-=0.00001*amount*intelligence;
            else Ground.waterAmount-=10;
        }
        if (World.temperature>=40&&intelligence>1000){
            if (0.00001*amount*intelligence<=40)
                World.temperature-=0.00001*amount*intelligence;
            else World.temperature-=10;
        }
        if ((int)intelligence/1000>0&&evilness>0)
            evilness=20-(int)intelligence/1000;

    }
    
}
