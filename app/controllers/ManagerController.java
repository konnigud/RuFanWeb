package controllers;

import is.ruframework.process.RuProcessRunner;
import play.mvc.*;
import views.html.index;

/**
 * Created by Konni on 25.10.2015.
 */


public class ManagerController extends Controller{

    public Result processplayers(){
        try{
            RuProcessRunner process = new RuProcessRunner("playerprocess.xml");
            process.run();
        } catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return redirect("/");
    }
}
