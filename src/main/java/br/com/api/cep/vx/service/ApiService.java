package br.com.api.cep.vx.service;

import br.com.api.cep.vx.dto.EnderecoDto;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ApiService {

    EnderecoDto enderecoDto = new EnderecoDto();

    // Esse método vai retornar uma classe DTO de endereco
    public EnderecoDto getEndereco(String cep) throws IOException, InterruptedException {

        try {

            HttpClient client = HttpClient.newHttpClient();

            // criando a requisição
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("viacep.com.br/ws/" + cep + "/json/")).build();


            // criando o response
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            // utilizando o jackson
            ObjectMapper mapper = new ObjectMapper();

            enderecoDto = mapper.readValue(response.body(), EnderecoDto.class);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return enderecoDto;

    }
}
