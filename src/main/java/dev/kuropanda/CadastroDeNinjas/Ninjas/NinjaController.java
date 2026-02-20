package dev.kuropanda.CadastroDeNinjas.Ninjas;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ninjas")
public class NinjaController {

    private final NinjaService ninjaService;

    public NinjaController(NinjaService ninjaService) {
        this.ninjaService = ninjaService;
    }

    @GetMapping("/boasvindas")
    @Operation(summary = "Mensagem de Boas Vindas", description = "Essa rota retorna uma mensagem de boas vindas para quem acessa.")
    public String boasVindas(){
        return "Boas vindas a essa rota!";
    }

    @PostMapping("/criar")
    @Operation(summary = "Cria um novo Ninja",description = "Rota cria um novo ninja e insere no banco de dados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Ninja criado com sucesso."),
            @ApiResponse(responseCode = "400", description = "Erro na criação do ninja.")
    })
    public ResponseEntity<String> criarNinja(@RequestBody NinjaDTO ninja){
        NinjaDTO novoNinja = ninjaService.criarNinja(ninja);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Ninja criado com sucesso: " + novoNinja.getNome() + " (ID): " + novoNinja.getId());
    }

    @GetMapping("/listar")
    public ResponseEntity<List<NinjaDTO>> listarNinjas(){
        List<NinjaDTO> ninjas = ninjaService.listarNinjas();
        return ResponseEntity.ok(ninjas);
    }

    @GetMapping("/listar/{id}")
    @Operation(summary = "Lista o ninja por ID",description = "Rota lista um ninja pelo ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ninja encontrado."),
            @ApiResponse(responseCode = "404", description = "Ninja não encontrado.")
    })
    public ResponseEntity<?> listarNinjaPorId(@PathVariable Long id){
        NinjaDTO ninjasID = ninjaService.listarNinjasPorId(id);
        if (ninjasID != null){
           return ResponseEntity.ok(ninjasID);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("O Ninja com o ID " + id + " não foi encontrado no banco de dados!");
        }
    }

    @PutMapping("/alterar/{id}")
    @Operation(summary = "Altera o ninja por ID",description = "Rota altera um ninja pelo ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ninja alterado com sucesso."),
            @ApiResponse(responseCode = "404", description = "Ninja não encontrado.")
    })
    public ResponseEntity<?> alterarNinjaPorId(
            @Parameter(description = "Usuário manda o ID no caminho da requisição")
            @PathVariable Long id,
            @Parameter(description = "Usuário manda os dados do ninja a ser atualizado no corpo da requisição")
            @RequestBody NinjaDTO ninjaAtualizado){
        NinjaDTO atualizarNinja = ninjaService.atualizarNinja(id, ninjaAtualizado);
        if (atualizarNinja != null){
            return ResponseEntity.status(HttpStatus.OK)
                    .body("O Ninja: " + atualizarNinja.getNome() + ", (ID): " + atualizarNinja.getId() + " foi atualizado com sucesso.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("O Ninja com o ID " + id + " não foi encontrado no banco  de dados!");
        }
    }

    @DeleteMapping("/deletar/{id}")
    @Operation(summary = "Deleta o ninja por ID",description = "Rota deleta um ninja pelo ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ninja deletado com sucesso."),
            @ApiResponse(responseCode = "404", description = "Ninja não encontrado.")
    })
    public ResponseEntity<String> deletarNinjaId(@PathVariable Long id){
        if (ninjaService.listarNinjasPorId(id) != null){
            ninjaService.deletarNinjaId(id);
            return ResponseEntity.ok("Ninja com ID " + id + " deletado com sucesso!");
        } else  {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("O Ninja com o ID " + id + " não foi encontrado!");
        }
    }

}
