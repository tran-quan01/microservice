import React from "react";
import "./index.css";

const products = [
  {
    id: 1,
    name: "Bò Khô Sợi",
    price: "180.000 VND / 500g",
    description: "Bò khô cay thơm ngon, làm thủ công 100%.",
    image: "https://your-s3-url.com/bo-kho.jpg",
  },
  {
    id: 2,
    name: "Gà Khô Lá Chanh",
    price: "150.000 VND / 500g",
    description: "Gà khô xé cay, vị đậm đà, siêu ngon.",
    image: "https://your-s3-url.com/ga-kho.jpg",
  },
  {
    id: 3,
    name: "Heo Khô Cháy Tỏi",
    price: "160.000 VND / 500g",
    description: "Heo khô cháy tỏi thơm lừng, không chất bảo quản.",
    image: "https://your-s3-url.com/heo-kho.jpg",
  },
];

function App() {
  const handleZalo = () => {
    window.location.href = "https://zalo.me/84968432076";
  };

  const handleCall = () => {
    window.location.href = "tel:84968432076";
  };

  const handleMail = () => {
    window.location.href = "mailto:quan.it.bk@gmail.com";
  };

  return (
    <div className="container">
      <h1 className="title">Sản phẩm khô – Giao hàng toàn quốc</h1>

      <div className="product-list">
        {products.map((item) => (
          <div className="product-card" key={item.id}>
            <img src={item.image} alt={item.name} className="product-img" />
            <h2>{item.name}</h2>
            <p className="price">{item.price}</p>
            <p>{item.description}</p>

            <div className="btn-group">
              <button onClick={handleZalo}>Nhắn Zalo</button>
              <button onClick={handleCall}>Gọi Điện</button>
              <button onClick={handleMail}>Gửi Email</button>
            </div>
          </div>
        ))}
      </div>
    </div>
  );
}

export default App;