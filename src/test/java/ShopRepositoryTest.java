import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ShopRepositoryTest {

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
    public void shouldThrowExceptionIfNotFound() {
        ShopRepository repo = new ShopRepository();
        Product item1 = new Product(1, "Книга", 500);

        repo.add(item1);

        // Проверяем выброс исключения
        Assertions.assertThrows(NotFoundException.class, () -> {
            repo.removeById(100);
        });
    }
}