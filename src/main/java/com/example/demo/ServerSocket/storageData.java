package com.example.demo.ServerSocket;

/**
 * 存储解析后String类型中16进制的传感器数据
 * @author dushikang
 * @version 1.0
 * @date 2019/9/18 19:22
 */
public class storageData extends Thread{
    private String data;

    public storageData(String data) {
        this.data=data;
    }

    public void run(){
        System.out.println(data);
        deal(data);
    }
    public void deal(String data){
        String[] dataArray=data.split(" ");

        //485传感器
        String sensor_currentValue = dataArray[3]+dataArray[4]+dataArray[5];//485传感器电流值
        String sensor_voltageValue = dataArray[7]+dataArray[8]+dataArray[9];//485传感器电压值
        //将16进制的数转化为10进制的数据
        Long sensor_currentValueNum = Long.parseLong(sensor_currentValue,16);
        Long sensor_voltageValueNum = Long.parseLong(sensor_voltageValue,16);

        //其他类型传感器
        String osensor_currentValue = dataArray[11]+dataArray[12]+dataArray[13];//电流
        String osensor_voltageValue = dataArray[15]+dataArray[16]+dataArray[17];//电压
        //将16进制的数转化为10进制的数据
        Long osensor_currentValueNum = Long.parseLong(osensor_currentValue,16);
        Long osensor_voltageValueNum = Long.parseLong(osensor_voltageValue,16);

        //电池信息
        String battery_currentValue = dataArray[19]+dataArray[20]+dataArray[21];
        String battery_voltageValue = dataArray[23]+dataArray[24]+dataArray[25];

        //光照值
        String illumination_value = dataArray[27]+dataArray[28]+dataArray[29]+dataArray[30];

        //将16进制的数转化为10进制的数据
        Long battery_currentValueNum = Long.parseLong(battery_currentValue,16);
        Long battery_voltageValueNum = Long.parseLong(battery_voltageValue,16);
        Long illumination_valueNum = Long.parseLong(illumination_value,16);

        System.out.println("485传感器：电流"+sensor_currentValueNum+" 电压："+sensor_voltageValueNum);
        System.out.println("其他传感器：电流"+osensor_currentValueNum+" 电压："+osensor_voltageValueNum);
        System.out.println("电池信息：电流"+battery_currentValueNum+" 电压："+battery_voltageValueNum);
        System.out.println("光照值"+illumination_valueNum);

        //**********************存储代码********************************//
    }
}
