package br.com.impacto.view;

import br.com.impacto.controller.CategoriaController;
import br.com.impacto.controller.ProdutoController;
import br.com.impacto.modelo.Categoria;
import br.com.impacto.modelo.Produto;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ProdutoCategoriaFrame extends JFrame {

    private final JLabel labelNome;
    private final JLabel labelDescricao;
    private final JLabel labelCategoria;
    private final JTextField textoNome;
    private final JTextField textoDescricao;
    private final JComboBox<Categoria> comboCategoria;
    private final JButton botaoSalvar;
    private final JButton botaoEditar;
    private final JButton botaoLimpar;
    private final JButton botaoApagar;
    private final JTable tabela;
    private final DefaultTableModel modelo;
    private final CategoriaController categoriaController;
    private final ProdutoController produtoController;

    public ProdutoCategoriaFrame() {
        super("Produtos");
        Container container = getContentPane();
        setLayout(null);

        categoriaController = new CategoriaController();
        produtoController = new ProdutoController();

        labelNome = new JLabel("Nome do Produto");
        labelDescricao = new JLabel("Descrição do Produto");
        labelCategoria = new JLabel("Categoria do Produto");

        labelNome.setBounds(10, 10, 240, 15);
        labelDescricao.setBounds(20, 50, 240, 15);
        labelCategoria.setBounds(10, 90, 240, 15);

        container.add(labelNome);
        container.add(labelDescricao);
        container.add(labelCategoria);

        textoNome = new JTextField();
        textoDescricao = new JTextField();
        comboCategoria = new JComboBox<>();

        comboCategoria.addItem(new Categoria(0, "Selecione"));
        List<Categoria> categorias = this.listarCategoria();

        for (Categoria categoria : categorias) {
            comboCategoria.addItem(categoria);
        }

        textoNome.setBounds(10, 25, 265, 20);
        textoDescricao.setBounds(10, 65, 265, 20);
        comboCategoria.setBounds(10, 105, 265, 20);

        container.add(textoNome);
        container.add(textoDescricao);
        container.add(comboCategoria);

        botaoSalvar = new JButton("Salvar");
        botaoLimpar = new JButton("Limpar");

        botaoSalvar.setBounds(10, 145, 80, 20);
        botaoLimpar.setBounds(100, 145, 100, 20);

        container.add(botaoSalvar);
        container.add(botaoLimpar);

        tabela = new JTable();
        modelo = (DefaultTableModel) tabela.getModel();

        modelo.addColumn("Identificador do Produto");
        modelo.addColumn("Nome do Produto");
        modelo.addColumn("Descrição do Produto");

        preencherTabela();

        tabela.setBounds(10, 185, 760, 300);
        container.add(tabela);

        botaoApagar = new JButton("Excluir");
        botaoEditar = new JButton("Alterar");

        botaoApagar.setBounds(10, 500, 80, 20);
        botaoEditar.setBounds(100, 500, 80, 20);

        container.add(botaoApagar);
        container.add(botaoEditar);

        setSize(800, 600);
        setVisible(true);
        setLocationRelativeTo(null);

        botaoSalvar.addActionListener(e -> {
            salvar();
            limparTabela();
            preencherTabela();
        });

        botaoLimpar.addActionListener(e -> limpar());

        botaoApagar.addActionListener(e -> {
            deletar();
            limparTabela();
            preencherTabela();
        });

        botaoEditar.addActionListener(e -> {
            alterar();
            limparTabela();
            preencherTabela();
        });

    }

    private void alterar() {
        Object objetoDaLinha = modelo.getValueAt(tabela.getSelectedRow(), tabela.getSelectedColumn());
        if (objetoDaLinha instanceof Integer) {
            this.produtoController.alterar(
                    new Produto(
                            (Integer) objetoDaLinha,
                            (String) modelo.getValueAt(tabela.getSelectedRow(), 1),
                            (String) modelo.getValueAt(tabela.getSelectedRow(), 2)
                    ));
        } else {
            JOptionPane.showMessageDialog(this, "Por favor, selecionar o ID");
        }
    }

    private void deletar() {
        Object objetoDaLinha = modelo.getValueAt(tabela.getSelectedRow(), tabela.getSelectedColumn());
        if (objetoDaLinha instanceof Integer) {
            this.produtoController.deletar((Integer) objetoDaLinha);
            modelo.removeRow(tabela.getSelectedRow());
            JOptionPane.showMessageDialog(this, "Item excluído com sucesso!");
        } else {
            JOptionPane.showMessageDialog(this, "Por favor, selecionar o ID");
        }
    }

    private void limpar() {
        this.textoNome.setText("");
        this.textoDescricao.setText("");
        this.comboCategoria.setSelectedIndex(0);
    }

    private void limparTabela() {
        modelo.getDataVector().clear();
    }

    private void salvar() {
        if (!textoNome.getText().equals("") && !textoDescricao.getText().equals("")) {
            Produto produto = new Produto(textoNome.getText(), textoDescricao.getText());
            Categoria categoria = (Categoria) comboCategoria.getSelectedItem();
            assert categoria != null;
            produto.setCategoriaId(categoria.getId());
            this.produtoController.salvar(produto);
            JOptionPane.showMessageDialog(this, "Salvom com sucesso!");
            this.limpar();
        } else {
            JOptionPane.showMessageDialog(this, "Nome e Descrição devem ser informados.");
        }
    }

    private void preencherTabela() {
        for (Produto produto : listarProduto()) {
            modelo.addRow(new Object[]{produto.getId(), produto.getNome(), produto.getDescricao()});
        }
    }

    private List<Produto> listarProduto() {
        return this.produtoController.listar();
    }

    private List<Categoria> listarCategoria() {
        return this.categoriaController.listar();
    }
}
