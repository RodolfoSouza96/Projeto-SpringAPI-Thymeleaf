package br.com.rodolfols.projetoo.controller;

import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.rodolfols.projetoo.controller.form.AlunoForm;
import br.com.rodolfols.projetoo.controller.form.AtualizacaoAlunoFormThymeleaf;
import br.com.rodolfols.projetoo.modelo.Aluno;
import br.com.rodolfols.projetoo.repository.AlunoRepository;

@Controller
@RequestMapping("/aluno")
public class AlunoControllerThymeLeaf {

	@Autowired
	private AlunoRepository alunoRepository;
	
	@GetMapping("/formulario")
	public String formulario(AlunoForm form) {
		return "aluno/formulario";
	}
		
	@PostMapping("/novo")
	public String novo(@Valid AlunoForm form, BindingResult result) {
		
		if(result.hasErrors()) {
			return "aluno/formulario";
		}
		
		Aluno aluno = form.converter();
		alunoRepository.save(aluno);
		
		return "redirect:/home";
	}
	
	@GetMapping("/listarPorId")
	public String listarPorId(Aluno formData) {
		return "aluno/listarPorId";
	}
	
	@PostMapping("/listarPorIdnovo")
    public String listaPorIdnovo(Aluno formData, Model model, Integer id) {
		Optional<Aluno> aluno = alunoRepository.findById(id);
		if(!aluno.isPresent()) {
			return "redirect:/home";
		} else {
			model.addAttribute("aluno", aluno.get());
			return "aluno/listarPorId";
		}
    }
	
	@GetMapping("/atualiza")
	public String atualiza(AtualizacaoAlunoFormThymeleaf form) {
			return "aluno/atualiza";
	}
	
	@PostMapping("/atualizanovo")
	@Transactional
    public String atualizanovo(@Valid AtualizacaoAlunoFormThymeleaf form, BindingResult result, Integer id) {
						
		if(result.hasErrors()) {
			return "aluno/atualiza";
		}
			
		Optional<Aluno> optional = alunoRepository.findById(id);
		if(optional.isPresent()) {
			form.atualizar(id, alunoRepository);
		}
		
		return "redirect:/home";
		
    }
	
	@GetMapping("/delete")
	public String delete(Aluno form) {
			return "aluno/delete";
	}
	
	@PostMapping("/deletenovo")
	@Transactional
	public String deletenovo(Aluno form, BindingResult result, Integer id) {
		
		if(result.hasErrors()) {
			return "aluno/delete";
		}
		
		Optional<Aluno> aluno = alunoRepository.findById(id);
		if(aluno.isPresent()) {
			alunoRepository.deleteById(id);
		}
		
		return "redirect:/home";
	}
	
	@ExceptionHandler(IllegalArgumentException.class)
	public String onError() {
		return "redirect:/home";
	}
	
	
	
	

	
	
	
}
