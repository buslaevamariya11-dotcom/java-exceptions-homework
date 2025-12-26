public class ShopRepository {
    private Product[] products = new Product[0];

    // Метод для добавления товара в массив
    public void add(Product product) {
        Product[] tmp = new Product[products.length + 1];
        for (int i = 0; i < products.length; i++) {
            tmp[i] = products[i];
        }
        tmp[tmp.length - 1] = product;
        products = tmp;
    }

    public Product[] findAll() {
        return products;
    }

    // Вспомогательный метод поиска товара по ID
    public Product findById(int id) {
        for (Product product : products) {
            if (product.getId() == id) {
                return product;
            }
        }
        return null; // Если не нашли — возвращаем "ничего"
    }

    // Метод удаления
    public void removeById(int id) {
        // Сначала проверяем, есть ли такой товар
        if (findById(id) == null) {
            // Если товара нет — выкидываем нашу ошибку
            throw new NotFoundException("Element with id: " + id + " not found");
        }

        // Если товар есть — удаляем его (создаем новый массив на 1 меньше)
        Product[] tmp = new Product[products.length - 1];
        int copyToIndex = 0;
        for (Product product : products) {
            if (product.getId() != id) {
                tmp[copyToIndex] = product;
                copyToIndex++;
            }
        }
        products = tmp;
    }
}