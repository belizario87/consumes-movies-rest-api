import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        //Fazer uma conexao HTTP e buscar os dados dosfilmes
        String url = "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopMovies.json";
        var endereco = URI.create(url);
        var cliente = HttpClient.newHttpClient();
        var request = HttpRequest.newBuilder(endereco).GET().build();
        var response = cliente.send(request, BodyHandlers.ofString());
        String body = response.body();

        // extrair os dados que interessam (titulo, poster, classificacao)

        var parser = new JsonParse();
        List<Map<String, String>> listaDeFilmes = parser.parse(body);

        //exibir e manipular os dados
        for (Map<String, String> filme : listaDeFilmes) {
            System.out.println("\u001b[1mTitulo: \u001b[m" + filme.get("title"));
            System.out.println("\u001b[1mUrl da Imagem: \u001b[m" + filme.get("image"));
            System.out.println("\033[45m\u001b[1mClassificacao\033[m:\u001b[m " + filme.get("imDbRating"));
            double rate = Double.parseDouble(filme.get("imDbRating"));
            int numeroEstrelinhas = (int) rate;

            for (int estrelas = 1; estrelas <= numeroEstrelinhas; estrelas++) {
                System.out.print("⭐");
            }
            System.out.println("\n");
        }

    }
}