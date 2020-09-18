package com.ouladakdimamine.quizzproject.Presenteurs;

import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;





import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import org.xml.sax.InputSource;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import java.io.IOException ;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.widget.ImageButton;
import android.widget.ProgressBar;

import com.ouladakdimamine.quizzproject.Modeles.Categorie;
import com.ouladakdimamine.quizzproject.Modeles.Proposition;
import com.ouladakdimamine.quizzproject.Modeles.Question;
import com.ouladakdimamine.quizzproject.Modeles.Quiz;
import com.ouladakdimamine.quizzproject.Presenteurs.Adapters.CategorieGameAdapter;
import com.ouladakdimamine.quizzproject.R;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class CategorieGameActivity extends Activity {
    private static final String LOG_TAG = "HttpClientGET";

    private RecyclerView catslist ;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private ImageButton xmlupdater;



    public static String getFirstLevelTextContent(Node node)
    {
        NodeList list = node.getChildNodes();
        StringBuilder textContent = new StringBuilder();
        for (int i = 0; i < list.getLength(); ++i)
        {
            Node child = list.item(i);
            if (child.getNodeType() == Node.TEXT_NODE)
                textContent.append(child.getTextContent());
        }
        return textContent.toString();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);


        HttpPage tacheHttpPage = new HttpPage() ;
        tacheHttpPage.execute() ;
    }



    int lastQuizId()
    {
        int x=0;
        List<Quiz> quizzs=Quiz.listAll(Quiz.class);
        x = quizzs.get(quizzs.size()-1).getId().intValue();
        return x;
    }

    int lastQuestionId()
    {
        int x=0;
        List<Question> questions=Question.listAll(Question.class);
        x = questions.get(questions.size()-1).getId().intValue();
        return x;
    }

    int lastCatId()
    {
        int x=0;
        List<Categorie> cats=Categorie.listAll(Categorie.class);
        x = cats.get(cats.size()-1).getId().intValue();
        return x;
    }

    int lastPropId()
    {
        int x=0;
        List<Proposition> props=Proposition.listAll(Proposition.class);
        x = props.get(props.size()-1).getId().intValue();
        return x;
    }


    static String convertStreamToString(java.io.InputStream is) {
        java.util.Scanner s = new java.util.Scanner(is).useDelimiter("\\A");
        return s.hasNext() ? s.next() : "";
    }

    private void getPage(String adresse) {
        BufferedReader bufferedReader = null;
        HttpURLConnection urlConnection = null ;
        try {

            URL url = new URL(adresse);

            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setReadTimeout(60000 /* milliseconds */);
            urlConnection.setConnectTimeout(30000 /* milliseconds */);
            urlConnection.setDoInput(true);
            int responseCode = urlConnection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                InputStream inputStream = urlConnection.getInputStream();

            /*    bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                String ligneLue = bufferedReader.readLine();
                while (ligneLue != null) {
                    Log.i(LOG_TAG, ligneLue);
                    ligneLue = bufferedReader.readLine();
                }*/
                List<Categorie> cats2=new ArrayList<>();
                cats2=Categorie.listAll(Categorie.class);
                if(cats2.size()<=0) {
                    try {

                        Categorie cat = new Categorie(0, "Categorie Importée");
                        cat.save();
                        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
                        DocumentBuilder db = dbf.newDocumentBuilder();
                        Log.i("xml parsing :", "chui la 1");
                        Document doc = db.parse(inputStream);
                        Log.i("xml parsing :", "chui la 1.5");
                        doc.getDocumentElement().normalize();


                        Log.i("xml parsing :", "chui la2");
                        NodeList nodeList = doc.getElementsByTagName("Quizz");


                        for (int i = 0; i < nodeList.getLength(); i++) {

                            Node node = nodeList.item(i);
                            Log.i("xml parsing :", "chui la 3");
                            Element quiz_element = (Element) node;

                            Quiz q = new Quiz(0, (quiz_element).getAttribute("type"), "", lastCatId());
                            q.save();

                            NodeList questionslist = quiz_element.getElementsByTagName("Question");
                            int nombre_question = questionslist.getLength();
                            for (int j = 0; j < nombre_question; j++) {
                                Element question_element = (Element) questionslist.item(j);
                                String s = getFirstLevelTextContent((Node) question_element);
                                NodeList reponse_list = question_element.getElementsByTagName("Reponse");
                                Element reponse_element = (Element) reponse_list.item(0);

                                int x = Integer.parseInt(reponse_element.getAttribute("valeur"));


                                Question question = new Question(0, lastQuizId(), s.trim(), x);
                                question.save();

                                NodeList propo_list = question_element.getElementsByTagName("Proposition");
                                int nombre_prop = propo_list.getLength();
                                for (int k = 0; k < nombre_prop; k++) {
                                    Element prop_element = (Element) propo_list.item(k);

                                    Proposition p = new Proposition(0, lastQuestionId(), (prop_element).getTextContent().trim());
                                    p.save();

                                    if (x == k + 1) {
                                        Question ap = Question.findById(Question.class, lastQuestionId());
                                        ap.setReponse(lastPropId());
                                        ap.save();
                                    }


                                }
                            }




                        }


                    } catch (Exception e) {

                    }
                }

            }
            else {
                Log.i(LOG_TAG, "Response : " + responseCode);
            }

        } catch (Exception e) {
            Log.e(LOG_TAG, e.getMessage());
        }
        finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    Log.e(LOG_TAG, e.getMessage());
                }
            }
            if (urlConnection != null) urlConnection.disconnect();
        }
    }

    private class HttpPage extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground (Void... params) {
            getPage("https://dept-info.univ-fcomte.fr/joomla/images/CR0700/Quizzs.xml") ;
            return null;
        }
        @Override
        protected void onPostExecute (Void result) {

            List<Categorie> cats=new ArrayList<>();

            catslist=(RecyclerView)findViewById(R.id.listecat);
            catslist.setHasFixedSize(true);
            layoutManager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
            catslist.setLayoutManager(layoutManager);


            cats=Categorie.listAll(Categorie.class);
            adapter = new CategorieGameAdapter(getApplicationContext(), cats);
            catslist.setAdapter(adapter);

            adapter.notifyDataSetChanged();
        }
    }
}
