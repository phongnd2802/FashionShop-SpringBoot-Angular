package com.caonguyen.fashionshop.services.impl;

import com.caonguyen.fashionshop.entities.product.SKUEntity;
import com.caonguyen.fashionshop.dtos.request.cart.AddCartRequest;
import com.caonguyen.fashionshop.dtos.request.cart.GetCartRequest;
import com.caonguyen.fashionshop.dtos.request.cart.UpdateCartRequest;
import com.caonguyen.fashionshop.dtos.response.cart.AddCartRes;
import com.caonguyen.fashionshop.dtos.response.cart.GetCartItemRes;
import com.caonguyen.fashionshop.dtos.response.cart.GetCartRes;
import com.caonguyen.fashionshop.dtos.response.cart.UpdateCartRes;
import com.caonguyen.fashionshop.dtos.response.product.SearchSKURes;
import com.caonguyen.fashionshop.entities.auth.AccountEntity;
import com.caonguyen.fashionshop.entities.cart.CartEntity;
import com.caonguyen.fashionshop.entities.cart.CartItemEntity;
import com.caonguyen.fashionshop.entities.product.SKUEntity;
import com.caonguyen.fashionshop.repositories.AccountRepository;
import com.caonguyen.fashionshop.repositories.CartItemRepository;
import com.caonguyen.fashionshop.repositories.CartRepository;
import com.caonguyen.fashionshop.repositories.SKURepository;
import com.caonguyen.fashionshop.services.ICartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class CartServiceImpl implements ICartService {
    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private SKURepository skuRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private CartItemRepository cartItemRepository;

    @Transactional
    @Override
    public AddCartRes addCart(AddCartRequest req) {
        AccountEntity accountEntity = accountRepository.findByEmail(req.getEmail());
        if (accountEntity != null) {
            CartEntity cartEntity = cartRepository.findByAccount(accountEntity);
            if (cartEntity == null) {
                cartEntity = new CartEntity();
                cartEntity.setAccount(accountEntity);
                cartEntity.setCountProduct(0);
                cartRepository.save(cartEntity);
            }
            SKUEntity skuEntity = skuRepository.findByCode(req.getSkuCode());
            if (skuEntity != null) {
                CartItemEntity cartItemEntity = cartItemRepository.findByCartIdAndSkuCode(cartEntity.getId(), skuEntity.getCode());
                if (cartItemEntity != null) {
                    cartItemEntity.setQuantity(cartItemEntity.getQuantity() + 1);
                } else {
                    cartItemEntity = new CartItemEntity();
                    cartItemEntity.setCart(cartEntity);
                    cartItemEntity.setSku(skuEntity);
                    cartItemEntity.setQuantity(1);
                    cartEntity.setCountProduct(cartEntity.getCountProduct() + 1);
                    cartEntity.getItems().add(cartItemEntity);
                }
                cartItemRepository.save(cartItemEntity);
                cartRepository.save(cartEntity);
                AddCartRes addCartRes = new AddCartRes();
                addCartRes.setSuccess(Boolean.TRUE);
                addCartRes.setMessage("Thêm sản phẩm thành công!");
                return addCartRes;
            } else {
                AddCartRes addCartRes = new AddCartRes();
                addCartRes.setSuccess(Boolean.FALSE);
                addCartRes.setMessage("Không tồn tại sản phẩm!");
                return addCartRes;
            }
        } else {
            AddCartRes addCartRes = new AddCartRes();
            addCartRes.setSuccess(Boolean.FALSE);
            addCartRes.setMessage("Chưa có tài khoản!");
            return addCartRes;
        }
    }

    @Transactional
    @Override
    public UpdateCartRes updateCart(UpdateCartRequest req) {
        CartEntity cartEntity = cartRepository.findById(req.getCartId()) .orElseThrow(() -> null);
        if (cartEntity != null) {
            SKUEntity skuEntity = skuRepository.findByCode(req.getSkuCode());
            if (skuEntity != null) {
                CartItemEntity cartItemEntity = cartItemRepository.findByCartIdAndSkuCode(cartEntity.getId(), skuEntity.getCode());
                if (req.isEdit() == true) {
                    if (cartItemEntity != null) {
                        cartItemEntity.setQuantity(cartItemEntity.getQuantity() + req.getQuantity());
                        cartItemRepository.save(cartItemEntity);

                        UpdateCartRes updateCartRes = new UpdateCartRes();
                        updateCartRes.setSuccess(Boolean.TRUE);
                        updateCartRes.setMessage("Thành công!");
                        return updateCartRes;
                    } else {
                        UpdateCartRes updateCartRes = new UpdateCartRes();
                        updateCartRes.setSuccess(Boolean.FALSE);
                        updateCartRes.setMessage("Sản phẩm không có trong giỏ hàng!");
                        return updateCartRes;
                    }
                } else {
                    if (cartItemEntity != null) {
                        int quantity = cartItemEntity.getQuantity() - req.getQuantity();
                        if (quantity > 0) {
                            cartItemEntity.setQuantity(quantity);
                            cartItemRepository.save(cartItemEntity);
                        } else {
                            Long cartItemId = cartItemEntity.getId();
                            cartEntity.getItems().remove(cartItemEntity);
                            cartEntity.setCountProduct(cartEntity.getCountProduct() - 1);
                            cartItemRepository.deleteById(cartItemId);
                            cartRepository.save(cartEntity);
                        }

                        UpdateCartRes updateCartRes = new UpdateCartRes();
                        updateCartRes.setSuccess(Boolean.TRUE);
                        updateCartRes.setMessage("Thành công!");
                        return updateCartRes;
                    } else {
                        UpdateCartRes updateCartRes = new UpdateCartRes();
                        updateCartRes.setSuccess(Boolean.FALSE);
                        updateCartRes.setMessage("Sản phẩm không có trong giỏ hàng!");
                        return updateCartRes;
                    }
                }
            } else {
                UpdateCartRes updateCartRes = new UpdateCartRes();
                updateCartRes.setSuccess(Boolean.FALSE);
                updateCartRes.setMessage("Không tim thấy sản phẩm!");
                return updateCartRes;
            }
        } else {
            UpdateCartRes updateCartRes = new UpdateCartRes();
            updateCartRes.setSuccess(Boolean.FALSE);
            updateCartRes.setMessage("Chưa tạo tài khoản!");
            return updateCartRes;
        }
    }

    @Override
    public GetCartRes getCart(GetCartRequest req) {
        AccountEntity accountEntity = accountRepository.findByEmail(req.getEmail());
        if (accountEntity != null) {
            CartEntity cartEntity = cartRepository.findByAccount(accountEntity);
            if (cartEntity != null) {
                GetCartRes getCartRes = new GetCartRes();
                getCartRes.setCartid(cartEntity.getId());
                getCartRes.setCountProduct(cartEntity.getCountProduct());
                List<GetCartItemRes> listCartItemRes = new ArrayList<>();
                List<CartItemEntity> cartItemEntityList = cartItemRepository.findByCart(cartEntity);
                if (cartItemEntityList != null) {
                    for (CartItemEntity cartItemEntity : cartItemEntityList) {
                        GetCartItemRes getCartItemRes = new GetCartItemRes();
                        getCartItemRes.setCartId(cartItemEntity.getCart().getId());
                        getCartItemRes.setId(cartItemEntity.getId());

                        SKUEntity skuEntity = skuRepository.findByCode(cartItemEntity.getSku().getCode());
                        SearchSKURes searchSKURes = new SearchSKURes();
                        searchSKURes.setCode(skuEntity.getCode());
                        searchSKURes.setColor(skuEntity.getColor());
                        searchSKURes.setCurrentPrice(skuEntity.getCurrentPrice());
                        searchSKURes.setOriginalPrice(skuEntity.getOriginalPrice());
                        searchSKURes.setSize(skuEntity.getSize());
                        searchSKURes.setStock(skuEntity.getStock());
                        searchSKURes.setImages(skuEntity.getImages());
                        getCartItemRes.setSku(searchSKURes);

                        getCartItemRes.setQuantity(cartItemEntity.getQuantity());
                        listCartItemRes.add(getCartItemRes);
                    }
                }
                getCartRes.setItems(listCartItemRes);
                return getCartRes;
            } else {
                GetCartRes getCartRes = null;
                return getCartRes;
            }
        } else {
            GetCartRes getCartRes = null;
            return getCartRes;
        }
    }
}
