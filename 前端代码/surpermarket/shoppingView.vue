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
          <el-form :model="form" label-width="120px">
            <el-form-item label="会员卡号">
              <el-input v-model="form.memberID" ></el-input>
            </el-form-item>
            <el-form-item label="货号">
              <el-input v-model="form.productID" ></el-input>
            </el-form-item>
            <el-form-item label="数量">
              <el-input v-model="form.quantity" type="number"></el-input>
            </el-form-item>
            <el-button type="primary" @click="addProduct">添加商品</el-button>
          </el-form>
          
          <el-table :data="cart" style="width: 100%; margin-top: 20px">
            <el-table-column prop="productID" label="货号"></el-table-column>
            <el-table-column prop="productName" label="商品名称"></el-table-column>
            <el-table-column prop="price" label="单价"></el-table-column>
            <el-table-column prop="quantity" label="数量"></el-table-column>
            <el-table-column prop="total" label="总价"></el-table-column>
          </el-table>
          
          <div style="margin-top: 20px;">
            <el-button type="success" @click="calculateTotal">计算总价</el-button>
            <el-tag type="info" style="margin-left: 10px;">总价: {{ totalPrice }}</el-tag>
          </div>

          <el-dialog title="会员登记" :visible.sync="dialogVisible">
            <el-form :model="newMember" label-width="120px">
              <el-form-item label="姓名">
                <el-input v-model="newMember.memberName"></el-input>
              </el-form-item>
              <el-form-item label="电话号码">
                <el-input v-model="newMember.telephone"></el-input>
              </el-form-item>
            </el-form>
            <div slot="footer" class="dialog-footer">
              <el-button @click="dialogVisible = false">取消</el-button>
              <el-button type="primary" @click="registerMember">登记</el-button>
            </div>
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
      form: {
        memberID: '',
        productID: '',
        quantity: 1,
      },
      cart: [],
      totalPrice: 0,
      dialogVisible: false,
      newMember: {
        memberName: '',
        telephone: '',
      },
      orderID:'',
      flag:1
    };
  },
  methods: {
    async addProduct() {
      try {
        axios.post("/api/product/getByID",{productID:this.form.productID})
        .then((result) =>{
          this.cart.push({
          productID: result.data.data.productID,
          productName: result.data.data.productName,
          price: result.data.data.price,
          quantity: this.form.quantity,
          total: result.data.data.price * this.form.quantity,
        });
        //  axios里面会自动转换为JSON格式   这两部分需要写在一起
          // const product = response;
          // console.log("h",product);
        })
      } catch (error) {
        console.error('添加商品失败', error);
      }
    },
    async calculateTotal() {
      let tempTotal = this.cart.reduce((sum, item) => sum + item.total, 0); //计算总价记录在total

      if (this.form.memberID) {
        axios.post("/api/member/getByID",{memberID:this.form.memberID}
      ).then((result)=>{
          console.log(result.data);
          console.log("打折");
          tempTotal *= 0.9; // 会员打9折
          console.log(tempTotal);
          this.totalPrice = tempTotal;
            // 记录订单表
          this.recordOrder(result.data.data.memberID);
          //记录订单详情表
          // this.recordOrderDetail();
        })
      } else if (tempTotal > 200) {
        this.dialogVisible = true;  //显示会员登记对话框
        this.totalPrice = tempTotal;
      }
      await this.updateInventory();
      this.$message({
          showClose: true,
          message: '购买成功',
          type: 'success'
        });
    },
    async registerMember() {
      try {
        axios.post("api/member/add", this.newMember);
        this.dialogVisible = false; //隐藏会员登记对话框
        this.open1();
      } catch (error) {
        console.error('会员登记失败', error);
      }
    },
    async updateInventory() {
      try {
        const updates = this.cart.map(item =>
          axios.post(`api/product/updata`, {
            productID: item.productID,
            quantity: item.quantity,

          })
        );
        await Promise.all(updates);
        console.log("库存更新成功");
      } catch (error) {
        console.error('库存更新失败', error);
      }
    },
    async recordOrder(memberID) {
      //录入订单表
      try{
        console.log('recordOrder',++this.flag);
        let orders = {
          memberID:memberID,
          orderDate: new Date().toISOString()
        }
        await axios.post("/api/order/getOrderID",orders)
        .then((result)=>{
          this.orderID = result.data.data;
          this.recordOrderDetail(this.orderID);
          console.log("ID---"+this.orderID);
          console.log("录入订单表成功");
        })
      } catch(error) {
        console.log("录入订单表失败");
      }
    },
    async recordOrderDetail(orderID){
      try {
        console.log('recordOrderDetail',++this.flag);
        this.cart.forEach(item => {
          const temp = {
            orderID: orderID,
            productID: item.productID,
            productName: item.productName,
            unitPrice: item.price,
            quantity: item.quantity,
            totalPrice: item.total
          };
          const result = axios.post("/api/orderDetails", temp);
          console.log("订单详情表：--"+result);
        });
      } catch (error) {
        console.error("录入订单详情表失败", error);
      }
    },
    open1() {
        this.$notify({
          title: '成功',
          message: '会员注册成功',
          type: 'success'
        });
      },
  },
};
</script>

<style scoped>
</style>