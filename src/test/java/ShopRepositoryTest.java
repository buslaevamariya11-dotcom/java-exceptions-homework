import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ShopRepositoryTest {

    // Тесты Задания №1 (Удаление)
    @Test
    public void shouldRemoveExisting() {
        ShopRepository repo = new ShopRepository();
        Product item1 = new Product(1, "Книга", 500);
        Product item2 = new Product(2, "Смартфон", 50000);

        repo.add(item1);
        repo.add(item2);
        repo.removeById(1);

        Product[] expected = {item2};
        Product[] actual = repo.findAll();
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldThrowNotFoundException() {
        ShopRepository repo = new ShopRepository();
        Product item1 = new Product(1, "Книга", 500);

        repo.add(item1);

        Assertions.assertThrows(NotFoundException.class, () -> {
            repo.removeById(100);
        });
    }

    // Тесты Задания №2 (Добавление и дубликаты)
    @Test
    public void shouldAddProduct() {
        ShopRepository repo = new ShopRepository();
        Product item = new Product(10, "Телевизор", 30_000);

        repo.add(item);

        Product[] expected = {item};
        Product[] actual = repo.findAll();
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldThrowAlreadyExistsException() {
        ShopRepository repo = new ShopRepository();
        Product item1 = new Product(1, "Книга", 500);
        Product item2 = new Product(1, "Дубликат книги", 600); // Тот же ID!

        repo.add(item1);

        Assertions.assertThrows(AlreadyExistsException.class, () -> {
            repo.add(item2);
        });
    }
}