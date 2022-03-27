class Product:
    def __init__(self, sku, product_name, bin_num, quantity):
        self.sku = sku
        self.product_name = product_name
        self.bin_num = bin_num
        self.quantity = quantity
#
# # getter methods
    def getSku(self):
        return self.sku
    def getProductName(self):
        return self.product_name
    def getBin(self):
        return self.bin_num
    def getQuantity(self):
        return self.quantity

# setter method
    def setQuantity(self, x):
        self.quantity = x

# p = []
#
# p1 = Product("SP7875", "TV", "T345", 50)
# p2 = Product("YE98767", "Bike", "T9876", 70)
# p3 = Product("TR87680", "Microwave", "T349", 50)
# p4 = Product("WE3456", "Iron", "T9875", 10)
# p5 = Product("SP7875", "Refrigerator", "T349", 70)
# p6 = Product("BH67655", "Monitor", "T345", 80)
#
# p.append(p1)
# p.append(p2)
# p.append(p3)
# p.append(p4)
# p.append(p5)
# p.append(p6)

# print(p1.sku + ' ' + p1.productName + ' ' + p1.bin + ' ' + str(p1.quantity))

#
#     # getter methods
#     def getSku(self):
#         return self.sku
#     def getProductName(self):
#         return self.productName
#     def getBin(self):
#         return self.bin
#     def getQuantity(self):
#         return self.quantity
#
#
# class Collection_Of_Products:
#     def _init_(self):
#         self.products=[]
#
#      def add_product(self, newProduct):
#         self.products.append(newProduct)
#
#
#     productCollection=Collection_Of_Products()
#
#     productCollection.add_product(Product("SP7875", "TV","T345", 50))
#     productCollection.add_product(Product("YE98767", "Bike", "T9876", 70))
#     productCollection.add_product(Product("TR87680", "Microwave", "T349", 50))
#     productCollection.add_product(Product("WE3456", "Iron", "T9875", 10))
#     productCollection.add_product(Product("SP7875", "Refrigerator", "T349", 70))
#     productCollection.add_product(Product("BH67655", "Monitor", "T345", 80))


# sku = ''
# productName = ''
# bin = ''
# quantity = 0
#
# def setProduct(self, sku, productName, bin, quantity):
#     self.sku = sku
#     self.productName = productName
#     self.bin = bin
#     self.quantity = quantity
#
#     p = []
#     for i in range (0,5):
#         p1 = Product()
#         p1.setProduct("SP7875", "TV","T345", 50)
#         p.append(p1)
#
