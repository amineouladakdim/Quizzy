package com.ouladakdimamine.quizzproject.Presenteurs;

import android.content.Context;
import com.ouladakdimamine.quizzproject.Modeles.Score;

public class ScoreController {
    private ScoreController()
    {}
    private static ScoreController INSTANCE = null;

    public static synchronized ScoreController getInstance()
    {
        if (INSTANCE == null)
        {   INSTANCE = new ScoreController();
        }
        return INSTANCE;
    }

    public  Score score;

    public void initScore(int cat)
    {
        score=new Score(0,0,cat);
    }

    public void saveScore(Context context)
    {
        score.save();
    }

}
