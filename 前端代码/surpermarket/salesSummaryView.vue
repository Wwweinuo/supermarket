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
          <el-button type="primary" @click="fetchSalesData">获取销售数据</el-button>
          <el-button type="success" @click="exportReport" :disabled="!salesData.length">导出报表</el-button>
          <div style="margin-top: 20px;">
            <el-card>
              <h2>销售总量：{{ totalQuantity }}</h2>
              <h2>销售总额：{{ totalSales }}</h2>
            </el-card>
          </div>
          <el-table :data="salesData" style="width: 100%; margin-top: 20px;">
            <el-table-column prop="productID" label="货号"></el-table-column>
            <el-table-column prop="productName" label="商品名称"></el-table-column>
            <el-table-column prop="quantitySold" label="销售量"></el-table-column>
            <el-table-column prop="totalSales" label="销售额"></el-table-column>
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
      salesData: [],
    };
  },
  computed: {
    totalQuantity() {
      return this.salesData.reduce((sum, item) => sum + item.quantitySold, 0);
    },
    totalSales() {
      return this.salesData.reduce((sum, item) => sum + item.totalSales, 0);
    }
  },
  methods: {
    async fetchSalesData() {
      try {
        const response = await axios.get('/api/salesSummary/get'); // 替换为你的实际后端API端点
        this.salesData = response.data.data;
      } catch (error) {
        this.$message.error('获取销售数据失败');
        console.error('获取销售数据失败', error);
      }
    },
    exportReport() {
      const data = this.salesData.map(item => ({
        '货号': item.productID,
        '商品名称': item.productName,
        '销售量': item.quantitySold,
        '销售额': item.totalSales,
      }));
      const ws = XLSX.utils.json_to_sheet(data);
      const wb = XLSX.utils.book_new();
      XLSX.utils.book_append_sheet(wb, ws, '销售报表');
      XLSX.writeFile(wb, '销售报表.xlsx');
    }
  }
}
</script>

<style scoped>

</style>
