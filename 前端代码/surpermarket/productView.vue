<template>
  <div>
    <el-container style="height: 700px; border: 1px solid #eee">
      <el-header style="font-size: 40px; background-color: #409Ea8">超市收银系统</el-header>
      <el-container>
        <el-aside width="200px" style="background-color: #dfffff">
          <el-menu :default-openeds="['1', '6']" style="background-color: #408Ea0">
            <el-submenu index="1">
              <template slot="title"><i class="el-icon-menu"></i>导航</template>
              <el-menu-item index="1-1">
                  <router-link to="/member">会员管理</router-link>
              </el-menu-item>
              <el-menu-item index="1-2">
                  <router-link to="/product">商品管理</router-link>
              </el-menu-item>
              <el-menu-item index="1-3">
                  <router-link to="/shopping">购物处理</router-link>
              </el-menu-item>
              <el-menu-item index="1-4">
                  <router-link to="/order">订单管理</router-link>
              </el-menu-item>
              <el-menu-item index="1-5">
                  <router-link to="/salesSummary">销售情况汇总</router-link>
              </el-menu-item>
              
            </el-submenu>
          </el-menu>
        </el-aside>

        <el-main>
          <el-button type="primary" @click="showAddProductDialog">添加商品</el-button>
          <el-table :data="products" style="width: 100%; margin-top: 20px;">
            <el-table-column prop="productID" label="货号"></el-table-column>
            <el-table-column prop="productName" label="商品名称"></el-table-column>
            <el-table-column prop="price" label="单价"></el-table-column>
            <el-table-column prop="stock" label="库存量"></el-table-column>
            <el-table-column label="操作">
              <template slot-scope="scope">
                <el-button size="mini" @click="showEditProductDialog(scope.row)">编辑</el-button>
                <el-button size="mini" type="danger" @click="deleteProduct(scope.row.productID)">删除</el-button>
              </template>
            </el-table-column>
          </el-table>
          <el-dialog title="添加商品" :visible.sync="addProductDialogVisible">
            <el-form :model="addProductData">
              <el-form-item label="商品名称">
                <el-input v-model="addProductData.productName"></el-input>
              </el-form-item>
              <el-form-item label="单价">
                <el-input v-model="addProductData.price"></el-input>
              </el-form-item>
              <el-form-item label="库存量">
                <el-input v-model="addProductData.stock"></el-input>
              </el-form-item>
            </el-form>
            <span slot="footer" class="dialog-footer">
              <el-button @click="addProductDialogVisible = false">取消</el-button>
              <el-button type="primary" @click="addProduct">确认</el-button>
            </span>
          </el-dialog>
          <el-dialog title="编辑商品" :visible.sync="editProductDialogVisible">
            <el-form :model="productForm">
              <el-form-item label="货号">
                <el-input v-model="productForm.productID" disabled></el-input>
              </el-form-item>
              <el-form-item label="商品名称">
                <el-input v-model="productForm.productName"></el-input>
              </el-form-item>
              <el-form-item label="单价">
                <el-input v-model="productForm.price"></el-input>
              </el-form-item>
              <el-form-item label="库存量">
                <el-input v-model="productForm.stock"></el-input>
              </el-form-item>
            </el-form>
            <span slot="footer" class="dialog-footer">
              <el-button @click="editProductDialogVisible = false">取消</el-button>
              <el-button type="primary" @click="editProduct">确认</el-button>
            </span>
          </el-dialog>
        </el-main>
      </el-container>
    </el-container>
  </div>
</template>

<script>
import axios from 'axios';
export default {
  data() {
    return {
      products: [],
      addProductDialogVisible: false,
      editProductDialogVisible: false,
      productForm: {
        productID: '',
        productName: '',
        price: 0,
        stock: 0
      },
      addProductData: {
        productName: '',
        price: 0,
        stock: 0
      }
    };
  },
  created() {
    this.fetchProducts();
  },
  methods: {
    async fetchProducts() {
      try {
        axios.get('api/product/get').then((result)=>{
          this.products = result.data.data;
          console.log("加载商品信息成功");
        })
      } catch (error) {
        this.$message.error('获取商品数据失败');
        console.error('获取商品数据失败', error);
      }
    },
    showAddProductDialog() {
      this.addProductData = {
        productName: '',
        price: 0,
        stock: 0
      };
      this.addProductDialogVisible = true;
    },
    async addProduct() {
      try {
        axios.post('api/product/add', this.addProductData)
        .then((result)=>{
          this.products.push(result.data.data);
          console.log("demo",this.products);
          this.addProductDialogVisible = false;
          this.$message.success('添加商品成功');
        });
      } catch (error) {
        this.$message.error('添加商品失败');
        console.error('添加商品失败', error);
      }
    },
    showEditProductDialog(product) {
      this.productForm.productID = product.productID;
      this.productForm.productName = product.productName;
      this.productForm.price = product.price;
      this.productForm.stock = product.stock;
      this.editProductDialogVisible = true;
    },
    async editProduct() {
      try {
        axios.post(`api/product/up`, this.productForm)
        .then(()=>{
          this.editProductDialogVisible = false;
          this.$message.success('修改商品成功');
        });
      } catch (error) {
        this.$message.error('修改商品失败');
        console.error('修改商品失败', error);
      }
    },
    async deleteProduct(productID) {
      try {
        axios.delete("api/product/delete",{
          params:{
            productID:productID
          }
        }).then(()=>{
          this.$message.success('删除商品成功');
        })
      } catch (error) {
        this.$message.error('删除商品失败');
        console.error('删除商品失败', error);
      }
    }
  }
}
</script>

<style scoped>
</style>
