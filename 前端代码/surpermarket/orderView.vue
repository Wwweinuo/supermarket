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
          <el-input 
            v-model="orderID" 
            placeholder="请输入订单编号" 
            style="width: 300px; margin-bottom: 20px;"
            @keyup.enter="fetchOrderDetails"
          >
            <el-button slot="append" icon="el-icon-search" @click="fetchOrderDetails"></el-button>
          </el-input>
          <el-button type="primary" @click="exportOrder" :disabled="!orderDetails">导出订单</el-button>
          <el-table v-if="orderDetails" :data="orderDetails" style="width: 100%; margin-top: 20px;">
            <el-table-column prop="productID" label="货号" width="180"></el-table-column>
            <el-table-column prop="productName" label="商品名称" width="180"></el-table-column>
            <el-table-column prop="quantity" label="商品数量" width="180"></el-table-column>
            <el-table-column prop="totalPrice" label="总价" width="180"></el-table-column>
          </el-table>
        </el-main>
      </el-container>
    </el-container>
  </div>
</template>

<script>
import axios from 'axios';
import * as XLSX from 'xlsx';

export default {
  data() {
    return {
      orderID: 0,
      orderDetails: [],
    };
  },
  methods: {
    async fetchOrderDetails() {
      try {
        axios.post("/api/order/get",{orderID:this.orderID})
        .then((result)=>{
          this.orderDetails = result.data.data;
        })
      } catch (error) {
        this.$message.error('获取订单详情失败');
        console.error('获取订单详情失败', error);
      }
    },
    exportOrder(){
      const data = this.orderDetails.map(item => ({
        '货号': item.productID,
        '商品名称': item.productName,
        '商品数量': item.quantity,
        '总价': item.totalPrice,
      }));
      const ws = XLSX.utils.json_to_sheet(data);
      const wb = XLSX.utils.book_new();
      XLSX.utils.book_append_sheet(wb, ws, '订单详情');
      XLSX.writeFile(wb, '订单详情.xlsx');
    }
  }
}
</script>

<style scoped>

</style>
