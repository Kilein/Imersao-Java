import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;


public class App {
    public static void main(String[] args) throws Exception {
        //fazer uma conexão HTTP e buscar os top 250 filmes
        String url = "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopTVs.json";
        URI adress = URI.create(url);
        var client = HttpClient.newHttpClient();
        var request = HttpRequest.newBuilder(adress).GET().build();
        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
        String body = response.body();

        //extrair os dados que interessam(titulo, poster e classificação)
        var parser = new JsonParser();
        List<Map<String, String>> listaDeFilmes = parser.parse(body);

        //exibir e manipular os dados
        for (Map<String,String> filme : listaDeFilmes) {
            System.out.println("\u001b[3m\u001b[31mTitúlo:\u001b[m " + filme.get("title"));   
            System.out.println("\u001b[3m\u001b[31mPoster:\u001b[m " + filme.get("image"));
            System.out.println("\u001b[3m\u001b[31mRank:\u001b[m " + filme.get("imDbRating"));   
            //double classificação = Double.parseDouble(filme.get("imDbRating"));
            //int numeroEstrelinhas = (int) classificação;
            //for (int i = 1; numeroEstrelinhas <= 5; i++) {
            //    System.out.print("\u2B50 ⭐");
            //}
            System.out.println("\n");
        }
    }
}
