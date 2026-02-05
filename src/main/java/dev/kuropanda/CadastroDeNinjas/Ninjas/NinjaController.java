package dev.kuropanda.CadastroDeNinjas.Ninjas;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
public class NinjaController {

    @GetMapping("/boasvindas")
    public String boasVindas(){
        return "Boas vindas a essa rota!";
    }

    // TODO: Criar Rota para adicionar, mostrar, atualizar e deletar um Ninja

    // Adicionar Ninja (READ)
    @PostMapping("/criar")
    public String criarNinja(){
        return "Ninja Criado";
    }

    // Mostrar todos os Ninjas (READ)
    @GetMapping("/listaninja")
    public String listarNinja(){
        return "Lista geral dos Ninjas";
    }

    // Mostrar Ninja por ID (READ)
    @GetMapping("/listaninjaid")
    public String listarNinjaId(){
        return "Lista Ninja por ID";
    }

    // Alterar dados do Ninja (UPDATE)
    @PutMapping("/alterarid")
    public String alterarNinjaId(){
        return "Alterar Ninja por ID";
    }

    // Deletar o Ninja (DELETE)
    @DeleteMapping("/deletaid")
    public String deletarNinjaId(){
        return "Deletar Ninja por ID";
    }

}
