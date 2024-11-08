<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>AutoRent - Locadora de Veículos Premium</title>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet">
    <!-- Font Awesome -->
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css" rel="stylesheet">
    <!-- Custom CSS -->
    <style>
        body {
            background-color: #f8f9fa;
            font-family: 'Roboto', sans-serif;
        }
        .navbar-brand {
            font-weight: bold;
            font-size: 1.5rem;
        }
        .hero-section {
            background: linear-gradient(rgba(0, 0, 0, 0.6), rgba(0, 0, 0, 0.6)), url('https://images.unsplash.com/photo-1568605117036-5fe5e7bab0b7?ixlib=rb-1.2.1&auto=format&fit=crop&w=1350&q=80');
            background-size: cover;
            background-position: center;
            color: white;
            padding: 100px 0;
        }
        .card {
            border: none;
            box-shadow: 0 0.5rem 1rem rgba(0, 0, 0, 0.15);
        }
        .card-header {
            background-color: #007bff;
            color: white;
            font-weight: bold;
        }
        .btn-custom {
            background-color: #007bff;
            color: white;
            font-weight: bold;
        }
        .btn-custom:hover {
            background-color: #0056b3;
            color: white;
        }
        .feature-icon {
            font-size: 2rem;
            color: #007bff;
        }
    </style>
</head>
<body>

<!-- Navbar -->
<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <div class="container">
        <a class="navbar-brand" href="#">AutoRent</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" veiculo_id="navbarNav">
            <ul class="navbar-nav ms-auto">
                <li class="nav-item">
                    <a class="nav-link active" href="#">Início</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#">Veículos</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#">Ofertas</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#">Contato</a>
                </li>
            </ul>
        </div>
    </div>
</nav>


<section class="hero-section text-center">
    <div class="container">
        <h1 class="display-4 mb-4">Alugue o Carro dos Seus Sonhos</h1>
        <p class="lead mb-5">Experimente o luxo e conforto com nossa frota premium de veículos.</p>
        <a href="#reserva" class="btn btn-primary btn-lg">Faça sua Reserva</a>
    </div>
</section>


<div class="container my-5">
    <div class="row">
        <!-- Reservation Form -->
        <div class="col-lg-8">
            <div class="card" veiculo_id="reserva">
                <div class="card-header text-center py-3">
                    <h3 class="mb-0">Faça sua Reserva de Veículo</h3>
                </div>
                <div class="card-body">
                    <form>
                        <div class="row mb-3">
                            <div class="col-md-6">
                                <label for="pickupAgency" class="form-label">Agência de Retirada</label>
                                <select class="form-select" veiculo_id="pickupAgency" required>
                                    <option value="">Selecione...</option>
                                    <option value="1">Aeroporto Internacional</option>
                                    <option value="2">Centro da Cidade</option>
                                    <option value="3">Shopping Plaza</option>
                                </select>
                            </div>
                            <div class="col-md-6">
                                <label for="vehicleType" class="form-label">Tipo de Veículo</label>
                                <select class="form-select" veiculo_id="vehicleType" required>
                                    <option value="">Selecione...</option>
                                    <option value="economico">Econômico</option>
                                    <option value="intermediario">Intermediário</option>
                                    <option value="suv">SUV</option>
                                    <option value="luxo">Luxo</option>
                                </select>
                            </div>
                        </div>

                        <div class="row mb-3">
                            <div class="col-md-6">
                                <label for="pickupDate" class="form-label">Data de Retirada</label>
                                <input type="date" class="form-control" veiculo_id="pickupDate" required>
                            </div>
                            <div class="col-md-6">
                                <label for="returnDate" class="form-label">Data de Devolução</label>
                                <input type="date" class="form-control" veiculo_id="returnDate" required>
                            </div>
                        </div>

                        <div class="row mb-3">
                            <div class="col-md-6">
                                <label for="returnAgency" class="form-label">Agência de Devolução</label>
                                <select class="form-select" veiculo_id="returnAgency" required>
                                    <option value="">Selecione...</option>
                                    <option value="1">Aeroporto Internacional</option>
                                    <option value="2">Centro da Cidade</option>
                                    <option value="3">Shopping Plaza</option>
                                </select>
                            </div>
                            <div class="col-md-6">
                                <label for="paymentMethod" class="form-label">Forma de Pagamento</label>
                                <select class="form-select" veiculo_id="paymentMethod" required>
                                    <option value="">Selecione...</option>
                                    <option value="credit">Cartão de Crédito</option>
                                    <option value="debit">Cartão de Débito</option>
                                    <option value="paypal">PayPal</option>
                                </select>
                            </div>
                        </div>

                        <button type="submit" class="btn btn-custom w-100 py-2">Reservar Agora</button>
                    </form>
                </div>
            </div>
        </div>


        <div class="col-lg-4">
            <div class="card">
                <div class="card-body">
                    <h4 class="card-title text-center mb-4">Por que escolher a AutoRent?</h4>
                    <div class="d-flex align-items-center mb-3">
                        <i class="fas fa-car feature-icon me-3"></i>
                        <div>
                            <h5 class="mb-0">Frota Premium</h5>
                            <p class="mb-0">Veículos de luxo e conforto</p>
                        </div>
                    </div>
                    <div class="d-flex align-items-center mb-3">
                        <i class="fas fa-shield-alt feature-icon me-3"></i>
                        <div>
                            <h5 class="mb-0">Segurança Garantida</h5>
                            <p class="mb-0">Todos os veículos são segurados</p>
                        </div>
                    </div>
                    <div class="d-flex align-items-center mb-3">
                        <i class="fas fa-map-marked-alt feature-icon me-3"></i>
                        <div>
                            <h5 class="mb-0">GPS Incluso</h5>
                            <p class="mb-0">Navegação fácil em qualquer lugar</p>
                        </div>
                    </div>
                    <div class="d-flex align-items-center">
                        <i class="fas fa-headset feature-icon me-3"></i>
                        <div>
                            <h5 class="mb-0">Suporte 24/7</h5>
                            <p class="mb-0">Assistência a qualquer momento</p>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<!-- Footer -->
<footer class="bg-dark text-white py-4">
    <div class="container">
        <div class="row">
            <div class="col-md-4">
                <h5>AutoRent</h5>
                <p>Sua parceira de confiança para locação de veículos premium.</p>
            </div>
            <div class="col-md-4">
                <h5>Links Rápidos</h5>
                <ul class="list-unstyled">
                    <li><a href="#" class="text-white">Sobre Nós</a></li>
                    <li><a href="#" class="text-white">Termos e Condições</a></li>
                    <li><a href="#" class="text-white">Política de Privacidade</a></li>
                </ul>
            </div>
            <div class="col-md-4">
                <h5>Contato</h5>
                <p>Email: contato@autorent.com</p>
                <p>Telefone: (11) 1234-5678</p>
            </div>
        </div>
    </div>
</footer>


<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.min.js"></script>
</body>
</html>