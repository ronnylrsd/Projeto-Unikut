package com.model.Unikut;

import java.util.LinkedList;

public class ContaUsuario extends Conta {
    private String idade;
    private String sexo;
    private String aniversario;
    private String estadoCivil;
    private LinkedList<String> amigos;
    private LinkedList<String> recados;
    private LinkedList<String> Match;
    private String situacao;
    private String senhaRecado;
    private LinkedList<String> Recadocomsenha;
    private LinkedList<String> Solicitacoes;
    
    public ContaUsuario(String log, String sen) {
        super(log,sen);
        this.amigos = new LinkedList<>();
        this.recados = new LinkedList<>();
        this.Match = new LinkedList<>();
        this.situacao = "espera";
        this.Solicitacoes = new LinkedList<>();
        this.Recadocomsenha = new LinkedList<>();
        this.senhaRecado = "";
    }

    public ContaUsuario(String log, String sen, String no) {
        super(log,sen,no);
        this.amigos = new LinkedList();
        this.recados = new LinkedList<>();
        this.Solicitacoes = new LinkedList<>();
        this.Recadocomsenha = new LinkedList<>();
        this.Match = new LinkedList<>();
        this.situacao = "espera";
        this.senhaRecado = "";
    }

    public ContaUsuario(String log) {
        super(log);
        this.amigos = new LinkedList<>();
        this.situacao = "espera";
        this.Match = new LinkedList<>();
        this.Solicitacoes = new LinkedList<>();
        this.senhaRecado = "";
    }
    
    public String getIdade() {
        return idade;
    }

    public void setIdade(String idade) {
        this.idade = idade;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getAniversario() {
        return aniversario;
    }

    public void setAniversario(String aniversario) {
        this.aniversario = aniversario;
    }

    public String getEstadoCivil() {
        return estadoCivil;
    }

    public void setEstadoCivil(String estadoCivil) {
        this.estadoCivil = estadoCivil;
    }

    public LinkedList<String> getAmigos() {
        return amigos;
    }

    private void setAmigos(LinkedList<String> amigos) {
        this.amigos = amigos;
    }

    public LinkedList<String> getRecados() {
        return recados;
    }

    private void setRecados(LinkedList<String> recados) {
        this.recados = recados;
    }
    
    public void setMatch(LinkedList<String> Match) {
        this.Match = Match;
    }

    public LinkedList<String> getMatch() {
        return Match;
    }
    
    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }

    public String getSituacao() {
        return this.situacao;
    }

    public void setSituacao() {
        this.situacao = "Match";
    }

    public String getsenhaRecado() {
        return senhaRecado;
    }

    public String getSenhaRecado() {
        return senhaRecado;
    }

    public void setSenhaRecado(String senhaRecado) {
        this.senhaRecado = senhaRecado;
    }

    public LinkedList<String> getSolicitacoes() {
        return Solicitacoes;
    }

    public void setSolicitacoes(LinkedList<String> Solicitacoes) {
        this.Solicitacoes = Solicitacoes;
    }

    public void setsenhaRecado(String senhaRecado) {
        this.senhaRecado = senhaRecado;
    }

    public LinkedList<String> getRecadocomsenha() {
        return Recadocomsenha;
    }

    public void setRecadocomsenha(LinkedList<String> Recadocomsenha) {
        this.Recadocomsenha = Recadocomsenha;
    }

    protected String buscaAmigos(String amigo) {
        for (String c : amigos) {
            if (c.contains(amigo) == true) {
                return amigo;
            }
        }
        return null;
    }

    public boolean AdicionaSolicitacao(String login) {
        if (this.Solicitacoes.contains(login)) {
            return false;
        } else {
            this.Solicitacoes.add(login);
            return true;
        }
    }

    protected int buscaSituacao(String amigo) {
        for (String c : Solicitacoes) {
            if (c.contains(amigo) == true) {
                return c.indexOf(amigo);
            }
        }
        return -1;
    }

    public String AdicionarAmigo(int index) {
        String log;
        log = this.Solicitacoes.get(index);
        this.amigos.add(log);
        this.Solicitacoes.remove(index);
        return log;
    }

    public LinkedList listaAmigos() {
        return amigos;
    }

    public LinkedList listaAmigosPendentes() {
        LinkedList<String> amigosPendentes = new LinkedList();
        for (int i = 0; i < amigos.size(); i++) {
            if (amigos.get(i).contains("Pendente")) {
                amigosPendentes.add(amigos.get(i));
            }
        }
        return amigosPendentes;
    }

    public void alteraStatusAmigo(String amigo) {
        for (int i = 0; i < amigos.size(); i++) {
            if (amigos.get(i).compareTo(amigo) == 0) {
                amigos.add(i, amigos.get(i).replace("Pendente", "Amigo"));
            }
        }
    }

    public boolean adicionaRecado(String lg, String recado) {
        return recados.add(lg + ": " + recado);
    }

    public boolean adicionaRecadoComSenha(String lg, String recado, String senha1) {
        setsenhaRecado(senha1);
        return Recadocomsenha.add(lg + ": " + recado);
    }

    public LinkedList listaRecados() {
        if (recados.isEmpty()) {
            return null;
        } else {
            return recados;
        }
    }

    public LinkedList listaRecadosComSenha(String senha2) {
        String SenhaReal = getsenhaRecado();
        if (SenhaReal.compareTo("") == 0) {
            return null;
        }
        if (SenhaReal.compareTo(senha2) == 0) {
            if (Recadocomsenha.isEmpty()) {
                return null;
            } else {
                return Recadocomsenha;
            }
        } else {
            return null;
        }

    }

    public boolean verificarMatch(String match) {
        return this.Match.contains(match + ": PENDENTE");
    }

    protected int buscaMatch(String amigo) {
        for (String c : Match) {
            if (c.contains(amigo) == true) {
                return c.indexOf(amigo);
            }
        }
        return 0;
    }

    public boolean AdicionarMatch(String passada, String flag) {
        if (this.Match.contains(passada)) {
            return false;
        } else if (flag.equals("Match")) {

            this.Match.add(passada + ": MATCH");
            return true;
        } else {
            this.Match.add(passada + ": PENDENTE");
            return true;
        }
    }

    public void AlteraMatch(String login) {
        int i = Match.indexOf(login + ": PENDENTE");
        Match.set(i, login + " MATCH");

    }

    public boolean ValidaMatch(String login) {
        if (this.Match.contains(login)) {
            return true;
        } else {
            return false;
        }

    }

    public LinkedList ExibirMatch() {
        if (this.Match.isEmpty()) {
            return null;
        } else {
            return Match;
        }
    }

    public void alterarDadosAdm(char resp, String info) {
        switch (resp) {
            case '1':
                this.setNome(info);
                break;
            case '2':
                this.setIdade(info);
                break;
            case '3':
                this.setSexo(info);
                break;
            case '4':
                this.setAniversario(info);
                break;
            case '5':
                this.setEstadoCivil(info);
                break;
        }
    }
}
