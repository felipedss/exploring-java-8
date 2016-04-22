package app;

import model.Cliente;
import model.Compra;
import model.Produto;

import java.math.BigDecimal;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.Month;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class App {

    public static void main(String[] args) {

        //Produtos
        Produto televisao = Produto.of("Televisão",
                Paths.get("c:/"),
                new BigDecimal(1000));

        Produto notebook = Produto.of("Notebook",
                Paths.get("c:/"),
                new BigDecimal(2000));

        Produto geladeira = Produto.of("Geladeira",
                Paths.get("c:/"),
                new BigDecimal(3200));

        Produto fogao = Produto.of("Fogão",
                Paths.get("c:/"),
                new BigDecimal(500));

        Produto smartPhone = Produto.of("Smartphone XX",
                Paths.get("c:/"),
                new BigDecimal(4120));

        //Clientes
        Cliente joao = Cliente.of("João");
        Cliente paulo = Cliente.of("Paulo");
        Cliente jose = Cliente.of("José");
        Cliente maria = Cliente.of("Maria");
        Cliente ana = Cliente.of("Ana");

        //Compras
        Compra compra1 = Compra.of(joao, LocalDate.now());
        compra1.addProduto(televisao);
        compra1.addProduto(fogao);

        Compra compra2 = Compra.of(joao, LocalDate.of(2013, Month.FEBRUARY, 20));
        compra2.addProduto(smartPhone);

        Compra compra3 = Compra.of(paulo, LocalDate.of(2014, Month.APRIL, 12));
        compra3.addProduto(televisao);
        compra3.addProduto(notebook);
        compra3.addProduto(geladeira);
        compra3.addProduto(fogao);
        compra3.addProduto(smartPhone);

        Compra compra4 = Compra.of(jose, LocalDate.now().plusDays(3));
        compra4.addProduto(smartPhone);

        Compra compra5 = Compra.of(maria, LocalDate.now().minusMonths(1));
        compra5.addProduto(fogao);
        compra5.addProduto(geladeira);

        Compra compra6 = Compra.of(ana, LocalDate.now().minusWeeks(5));
        compra6.addProduto(smartPhone);
        compra6.addProduto(notebook);
        compra6.addProduto(televisao);

        Collection<Compra> compras = Arrays.asList(compra1, compra2, compra3, compra4, compra5, compra6);

        System.out.println("\nRelatórios");
        System.out.println();

        System.out.println("\nLista de Compras - Ordenada por data");
        compras.stream()
                .sorted(Comparator.comparing(Compra::getData))
                .forEach(System.out::println);

        System.out.println("\nSoma de todas compras");
        BigDecimal total = compras.stream()
                .map(Compra::totalCompra)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        System.out.println(total);

        System.out.println("\nProdutos mais comprados (quantidade)");
        Map<Produto, Long> topProducts = compras.stream()
                .flatMap(compra -> compra.getProdutos().stream())
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        topProducts.entrySet().stream()
                .sorted(Comparator.comparing(Map.Entry::getValue))
                .forEach(System.out::println);

        System.out.println("\nO produto mais comprado");
        topProducts.entrySet().stream()
                .max(Comparator.comparing(Map.Entry::getValue))
                .ifPresent(System.out::println);

        System.out.println("\nTotal comprado por produto");
        Map<Produto, BigDecimal> valorCompradoPorProduto = compras.stream()
                .flatMap(compra -> compra.getProdutos().stream())
                .collect(Collectors.groupingBy(Function.identity(), //t -> t
                        Collectors.reducing(BigDecimal.ZERO, Produto::getPreco, BigDecimal::add)));
        valorCompradoPorProduto.entrySet().stream()
                .sorted(Comparator.comparing(Map.Entry::getValue))
                .forEach(System.out::println);

        System.out.println("\nCompras por data");
        Map<LocalDate, List<Compra>> comprasPorData = compras.stream()
                .collect(Collectors.groupingBy(Compra::getData, Collectors.toList()));
        comprasPorData.entrySet().stream()
                .forEach(System.out::println);
    }
}
